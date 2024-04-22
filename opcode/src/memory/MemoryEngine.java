package memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import memory.handler.SectionsHandler;
import memory.parent.PhyMemoryObj;
import memory.util.MemoryAddressUtil;
import peload.ParserEngine;
import peload.headerbean.OptionalHeader;
import util.PrintUtil;


/**
 * 
 * @author youy
 * 根据节的首地址加偏移地址计算出实际位置
 *
 */
@Service
public class MemoryEngine extends PhyMemoryObj{
   private String baseSectionAddress;
	public void loadFromPeFile(String pePath){
		try{
			 parserEngine=new ParserEngine();
			 parserEngine.loadPeFile(pePath);
			 //PrintUtil.printHexArray(l.getPefilelst());
			 parserEngine.decode(parserEngine);
			 loadPeSections();
			 getCodeOfRva(getStartEip());
	    	}catch(Exception ex){
		} 
	}
	
	
	public String getStartEip(){
		OptionalHeader optionalHeader = (OptionalHeader) parserEngine.getParameter(OptionalHeader.class.getSimpleName());
		String baseAddress=optionalHeader.getImageBase().toHexString();
		String addressEntryPoint=optionalHeader.getAddressOfEntryPoint().toHexString();//其实是偏移量
		String rvAddress=MemoryAddressUtil.addressAdd(baseAddress, addressEntryPoint);
		return rvAddress;
	}
	
	
	public List<Integer> getCodeOfRva(String rvAddress){
		return getCodeOfRva(rvAddress,15);
	}
	
	/**
	 * 根据节的rva与该地址的rva的差知道了该指令实际为于集合的什么位置
	 * @param rvAddress
	 * @param size
	 * @return
	 */
	public List<Integer> getCodeOfRva(String rvAddress,int size){
		List<Integer> codeOfCpuLst=new ArrayList<Integer>();
		if(baseSectionAddress==null)baseSectionAddress=getBsAddress(rvAddress);
		int offsetIndex=MemoryAddressUtil.addressSubtract(rvAddress, baseSectionAddress);
		Map<String,List<Integer>>  sectionMap=sectionBean.getSectionMap();
		List<Integer> codeLst=sectionMap.get(baseSectionAddress);
		int sectionLength=codeLst.size();
		if(offsetIndex>sectionLength||offsetIndex<0){
			baseSectionAddress=getBsAddress(rvAddress);
			offsetIndex=MemoryAddressUtil.addressSubtract(rvAddress, baseSectionAddress);
		}
		int getSize=offsetIndex+size<codeLst.size()?offsetIndex+size:codeLst.size();
		for (int i = offsetIndex; i < getSize; i++) {
				codeOfCpuLst.add(codeLst.get(i));
			}
	    return codeOfCpuLst;
	}
	
	public List<Integer> getCodeOfRvaEndZero(String rvAddress){
        List<Integer> codeOfCpuLst=new ArrayList<Integer>();
        if(baseSectionAddress==null)baseSectionAddress=getBsAddress(rvAddress);
        int offsetIndex=MemoryAddressUtil.addressSubtract(rvAddress, baseSectionAddress);
        Map<String,List<Integer>>  sectionMap=sectionBean.getSectionMap();
        List<Integer> codeLst=sectionMap.get(baseSectionAddress);
        int sectionLength=codeLst.size();
        if(offsetIndex>sectionLength||offsetIndex<0){
            baseSectionAddress=getBsAddress(rvAddress);
            offsetIndex=MemoryAddressUtil.addressSubtract(rvAddress, baseSectionAddress);
        }
        for (int i = offsetIndex; i < codeLst.size(); i++) {
            Integer codeValue=codeLst.get(i);
            if(codeValue==0)break;
            codeOfCpuLst.add(codeValue);
            }
        return codeOfCpuLst;
    }
	
	
	/**
	 * 计算该rva为于哪个节中
	 * @param rvAddress
	 * @return
	 */
	private String getBsAddress(String rvAddress){
		Map<String,List<Integer>>  sectionMap=sectionBean.getSectionMap();
		Set<String> key=sectionMap.keySet();
		String selectedBsAddress="";
		int offsetI=0;
		int i=0;
		for (String bsAddress : key) {
			int offsetIndex=MemoryAddressUtil.addressSubtract(rvAddress, bsAddress);
			if(i++==0){
				selectedBsAddress=bsAddress;
				offsetI=offsetIndex;
				continue;
			}
			if(offsetIndex>0&&offsetIndex<offsetI){
				selectedBsAddress=bsAddress;
				offsetI=offsetIndex;
			}
		}
		//System.out.println(selectedBsAddress+":"+offsetI);
		return selectedBsAddress;
	}
	
	
	/**
	 * 载入节信息
	 */
	 private void loadPeSections(){
		 SectionsHandler s=new SectionsHandler(parserEngine,sectionBean);
		 s.doLoadPeSections();
	 }
	 
	 private void test(){
		 Map<String,List<Integer>>  sectionMap=sectionBean.getSectionMap();
		 Set key = sectionMap.keySet();
		 for (Object obj:key) {
			 String id=(String)obj;
			 System.out.println(id);
		}
		 
		// List<Integer> mm=sectionMap.get("00402000");
		// PrintUtil.printHexOfLstArray("MemoryEngine.test",mm);
	 }
	 
	 
	 private void test_getBsAddress(){
		String sAddress= getBsAddress("00401000");
	    System.out.println("sectionAddress:"+sAddress);
	 }
	 
	 private void test_getCodeOfRva(){
		 List<Integer> codeLst= getCodeOfRva("00401000");
		 PrintUtil.printHexOfLstArray("test:",codeLst);
	 }
    
	 
	 
	 
	 public static void main(String[] args) {
		 MemoryEngine m=new MemoryEngine();
		 //String path="F:\\vm\\cpu\\是男人就撑过20秒.exe";
		//String path="G:\\redmoon\\redmoon38\\RMC.exe";
		//String path="G:\\pe4.8.5\\PRO.exe";
		String path="G:\\cpu\\是男人就撑过20秒.exe";
		 m.loadFromPeFile(path);
		 m.test_getCodeOfRva();
	}
	 
}
