package peload.parser.peheader;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;

import peload.headerbean.FileHeader;
import peload.headerbean.PeHeader;
import peload.parent.ParserObj;
import structs.DWord;
import structs.Word;

@Controller
public class FileHeaderParser extends ParserObj{
	
	@Override
	public void decode(ParserObj parserObj) {
		ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		FileHeader fileHeader=new FileHeader();
		PeHeader header = (PeHeader) parserObj.getParameter(PeHeader.class.getSimpleName());
		int fileHeaderStartIndex=header.getEndPeHeaderIndex();
		
		//节的大小
		 int sizeOfNumberOfSections=fileHeaderStartIndex+Word.getSize();
		 Word numberOfSections=new Word(codeLst,sizeOfNumberOfSections);
		 fileHeader.setNumberOfSections(numberOfSections);
		 
		 
		//可选头大小
	    int sizeOfOptionalHeaderIndex=fileHeaderStartIndex+Word.getSize()*2+DWord.getSize()*3;
	    Word optionHeaderSize=new Word(codeLst,sizeOfOptionalHeaderIndex);
	    int fileHeaderEndIndex=fileHeaderStartIndex+20;
	    fileHeader.setSizeOfOptionalHeader(optionHeaderSize);
	   
	   //结束位置
	    fileHeader.setFileHeaderEndIndex(fileHeaderEndIndex);
	    
	    
		parserObj.addMemoryBean(fileHeader);
		
	}
  }
