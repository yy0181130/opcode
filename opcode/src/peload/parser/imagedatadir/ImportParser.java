package peload.parser.imagedatadir;

import java.util.ArrayList;
import java.util.List;

import memory.MemoryEngine;
import memory.bean.SectionsBean;
import memory.handler.SectionsHandler;
import memory.util.MemoryAddressUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import peload.headerbean.OptionalHeader;
import peload.headerbean.imagedatadirbean.ImageDataDirectoryBean;
import peload.headerbean.imagedatadirbean.componet.ImageThunkData32Bean;
import peload.headerbean.imagedatadirbean.imageimport.ImageImportDescriptorBean;
import peload.parent.ParserObj;
import structs.DWord;
import structs.Word;
import util.ArrayUtil;
import util.AsciiUtil;
import util.ByteUtil;

/**
 * 输入表解析
 * @author :youy
 * @time:2014年5月13日
 */
@Service
public class ImportParser extends ParserObj{
	public static Logger logger = LogManager.getLogger(ImportParser.class);
	@Override
	public void decode(ParserObj parserObj) {
		//ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		OptionalHeader optionalHeader = (OptionalHeader) parserObj.getParameter(OptionalHeader.class.getSimpleName());
		ImageDataDirectoryBean bean=optionalHeader.getLst().get(1);//输入表结构
		MemoryEngine memoryE=new MemoryEngine();
		SectionsBean sectionBean=new SectionsBean();
		SectionsHandler s=new SectionsHandler(parserObj,sectionBean);
		s.doLoadPeSections();
		memoryE.setSectionBean(sectionBean);
		String rvaAddress=bean.getVirtualAddress().toHexString();
		String baseAddress=optionalHeader.getImageBase().toHexString();
		String virtualAddress=MemoryAddressUtil.addressAdd(baseAddress, rvaAddress);
		int size=bean.getSize().toDecValue();
		ArrayList<Integer> codeLst=(ArrayList<Integer>)memoryE.getCodeOfRva(virtualAddress, size);//所有的输入表的字节长度;

		do_IMAGE_IMPORT_DESCRIPTOR(optionalHeader,codeLst,memoryE,baseAddress);
	}
	
	/**
	 * dll块的解析
	 * @param optionalHeader
	 * @param codeLst
	 * @param memoryE
	 * @param baseAddress
	 */
	private void do_IMAGE_IMPORT_DESCRIPTOR(OptionalHeader optionalHeader,ArrayList<Integer> codeLst,MemoryEngine memoryE,String baseAddress){
		//DWord characteristics=new DWord(codeLst,0,true);
		if(ArrayUtil.isArrayZero(codeLst,20))return;
	    ImageImportDescriptorBean b=new ImageImportDescriptorBean();
		DWord originalFirstThunk=new DWord(codeLst,0,true);//得到函数名称的相对虚拟地址
        DWord timeDateStamp=new DWord(codeLst,0,true);
		DWord forwarderChain=new DWord(codeLst,0,true);
		
		DWord name=new DWord(codeLst,0,true);
		String nameVrs=MemoryAddressUtil.addressAdd(name.toHexString(), baseAddress);
		ArrayList<Integer> nameAsciiLst=(ArrayList<Integer>)memoryE.getCodeOfRva(nameVrs, 100);
		String nameValue=AsciiUtil.getAsciiFromCodeLst(nameAsciiLst);
		
		DWord firstThunk=new DWord(codeLst,0,true);
		b.setOriginalFirstThunk(originalFirstThunk);
	    b.setTimeDateStamp(timeDateStamp);
		b.setForwarderChain(forwarderChain);
		b.setNameValue(nameValue);
		b.setName(name);
		b.setFirstThunk(firstThunk);
		String medAddressVirtualAddress=MemoryAddressUtil.addressAdd(firstThunk.toHexString(), baseAddress);//函数名称的虚拟地址
		do_GetMedVirtualAddressCol(b,medAddressVirtualAddress,memoryE,baseAddress);
		
		optionalHeader.addImportDesBean(b);
		do_IMAGE_IMPORT_DESCRIPTOR(optionalHeader,codeLst,memoryE,baseAddress);
	}
	
	/**
	 * 根据函数地址的虚拟地址计算出这个DLL共导入了多少个函数到这个文件中
	 * @param b
	 * @param medAddressVirtualAddress
	 */
	private void do_GetMedVirtualAddressCol(ImageImportDescriptorBean b,String medAddressVirtualAddress,MemoryEngine memoryE,String baseAddress){
	    List<Integer> thunkData32Lst=(ArrayList<Integer>)memoryE.getCodeOfRva(medAddressVirtualAddress, 4);
	    DWord medNameRelativeAddress=new DWord(thunkData32Lst,0);//该内容是该函数名称的相对地址
	    if(medNameRelativeAddress.toDecValue()==0) return;
	    ImageThunkData32Bean bean=new ImageThunkData32Bean();
	    bean.setVirtualAddress(new DWord(medAddressVirtualAddress));
	    bean.setMedNameRelativeAddress(medNameRelativeAddress);
	    do_ImageThunkData(bean,medNameRelativeAddress,memoryE,baseAddress);
	    b.addMed(bean);
	    String  newMedVirtualAddress=MemoryAddressUtil.addressAdd(medAddressVirtualAddress, 4);
	    do_GetMedVirtualAddressCol(b,newMedVirtualAddress,memoryE,baseAddress);
	}
	
	/**
	 * 取函数名称的解析
	 * @param b
	 * @param memoryE
	 * @param rvaOfMeds
	 * @param baseAddress
	 */
	private void do_ImageThunkData(ImageThunkData32Bean bean,DWord medNameRelativeAddress,MemoryEngine memoryE,String baseAddress){
	   //取得函数的虚拟地址;
	   boolean isFirstZero= ByteUtil.firstBitIsZero(medNameRelativeAddress);
	   if(!isFirstZero){ 
	      //logger.info("如果最高位为1，则表示函数以序号的方式从DLL中导出引用，低31位表示序号");
		  String hexValue=medNameRelativeAddress.toHexString();
	      StringBuffer hexBfu=new StringBuffer(hexValue);
	      hexBfu.replace(0, 1, "0");
	      bean.setAsciiName(hexBfu.toString());
		  return;
	   }
       String virtualAddressStr=MemoryAddressUtil.addressAdd(medNameRelativeAddress.toHexString(), baseAddress);
	   //取函数名的操作
	   List<Integer> hintByteLst=(ArrayList<Integer>)memoryE.getCodeOfRva(virtualAddressStr, 2);
	   Word hint=new Word(hintByteLst,0,true);
	   bean.setHint(hint);
	   String  nameVirtualAddress=MemoryAddressUtil.addressAdd(virtualAddressStr, 2);
	   List<Integer> nameAsciiLst=(List<Integer>)memoryE.getCodeOfRvaEndZero(nameVirtualAddress);
	   String asciiName=AsciiUtil.getAsciiFromCodeLst(nameAsciiLst);
	   bean.setAsciiName(asciiName);
	}
	
}
