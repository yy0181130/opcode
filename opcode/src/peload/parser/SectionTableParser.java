package peload.parser;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import peload.headerbean.FileHeader;
import peload.headerbean.OptionalHeader;
import peload.headerbean.SectionTableHeader;
import peload.headerbean.SectionTableHeaderLst;
import peload.parent.ParserObj;
import structs.AsciiBytes;
import structs.DWord;
import structs.Word;


@Service
public class SectionTableParser extends ParserObj{
   
	
	@Override
	public void decode(ParserObj parserObj) {
		ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		SectionTableHeaderLst sectionTableLst=new SectionTableHeaderLst();
		OptionalHeader optionalHeader = (OptionalHeader) parserObj.getParameter(OptionalHeader.class.getSimpleName());
		FileHeader fileHeader = (FileHeader) parserObj.getParameter(FileHeader.class.getSimpleName());
		
		int startIndex=optionalHeader.getEndIndex();
		
		Word sectionsNumber=fileHeader.getNumberOfSections();
		for (int i = 0; i < sectionsNumber.getCodeValue(); i++) {
			SectionTableHeader sectionTHeaderBean=new SectionTableHeader();
			
			AsciiBytes asciiByte=new AsciiBytes(codeLst,startIndex,8);
			sectionTHeaderBean.setName(asciiByte);
			
			//块的RVA
			int virtualAddressIndex=startIndex+asciiByte.getSize()+DWord.getSize();
			DWord virtualAddress=new DWord(codeLst,virtualAddressIndex);
			sectionTHeaderBean.setVirtualAddress(virtualAddress);
			
			//块数据大小
			int sizeOfRawDataIndex=virtualAddressIndex+DWord.getSize();
			DWord sizeOfRawData=new DWord(codeLst,sizeOfRawDataIndex);
			sectionTHeaderBean.setSizeOfRawData(sizeOfRawData);
			
			//块数据在文件中的偏移量
			int pointerToRawDataIndex=sizeOfRawDataIndex+DWord.getSize();
			DWord pointerToRawData=new DWord(codeLst,pointerToRawDataIndex);
			sectionTHeaderBean.setPointerToRawData(pointerToRawData);
			
			//块的属性标志
			int characteristicsIndex=pointerToRawDataIndex+DWord.getSize()*4;
			DWord characteristics=new DWord(codeLst,characteristicsIndex);
			sectionTHeaderBean.setCharacteristics(characteristics);
			
			sectionTableLst.addSectionTable(sectionTHeaderBean);
			startIndex=characteristicsIndex+DWord.getSize();
		}
		parserObj.addMemoryBean(sectionTableLst);
		
	}
 }
