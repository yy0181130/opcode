package peload.parser.peheader;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import peload.headerbean.FileHeader;
import peload.headerbean.OptionalHeader;
import peload.headerbean.imagedatadirbean.ImageDataDirectoryBean;
import peload.headerbean.imagedatadirbean.ImageDataDirectoryType;
import peload.parent.ParserObj;
import structs.DWord;
import structs.UByte;
import structs.Word;


@Controller
public class OptionalHeaderParser extends ParserObj{
    public static Logger logger = LogManager.getLogger(OptionalHeaderParser.class);
	@Override
	public void decode(ParserObj parserObj) {
		ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		FileHeader fileHeader = (FileHeader) parserObj.getParameter(FileHeader.class.getSimpleName());
		OptionalHeader optionHeader=new OptionalHeader();
		
		//程序起始RVA
	    int optionStartIndex=fileHeader.getFileHeaderEndIndex();
	    int addressOfEntryPointIndex=optionStartIndex+Word.getSize()+UByte.getSize()*2+DWord.getSize()*3;
		DWord addressOfEntryPoint=new DWord(codeLst,addressOfEntryPointIndex);
	    optionHeader.setAddressOfEntryPoint(addressOfEntryPoint);
	    
	    //代码段起始RVA
	    int baseOfCodeIndex=addressOfEntryPointIndex+DWord.getSize();
		DWord baseOfCode=new DWord(codeLst,baseOfCodeIndex);
		optionHeader.setBaseOfCode(baseOfCode);
		
		//装入基址
		int imageBaseIndex=optionStartIndex+Word.getSize()+UByte.getSize()*2+DWord.getSize()*6;
		DWord imageBase=new DWord(codeLst,imageBaseIndex);
		//System.out.println(imageBase.toHexString());
		optionHeader.setImageBase(imageBase);
        
		//装入数据目录表
		int imgDtaDirIndex=optionStartIndex+Word.getSize()*9+UByte.getSize()*2+DWord.getSize()*19;
		for (int i = 0; i < 16; i++) {
			DWord virtualAddress=new DWord(codeLst,imgDtaDirIndex);
            DWord size=new DWord(codeLst,imgDtaDirIndex+DWord.getSize());//该位置保存的是该目录的大小
			ImageDataDirectoryType type=ImageDataDirectoryType.getTypeByIndex(i);
			ImageDataDirectoryBean b=new ImageDataDirectoryBean(virtualAddress,size,type);
			optionHeader.getLst().add(b);
			imgDtaDirIndex=imgDtaDirIndex+DWord.getSize()*2;
		}
		//可选头结束位置
		//包括了DataDirectory,这个地方需要做一个区别  最后的是个数组;一共有16组
		int endIndex=optionStartIndex+Word.getSize()*9+UByte.getSize()*2+DWord.getSize()*19+DWord.getSize()*2*16;
		optionHeader.setEndIndex(endIndex);
		
		parserObj.addMemoryBean(optionHeader);
		//PrintUtil.printHexArray(x);
	}
   
}
