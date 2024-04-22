package peload.parser;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import peload.headerbean.DosHeader;
import peload.parent.ParserObj;
import structs.DWord;

@Service
public class MsDosHeaderParser extends ParserObj{
      
	@Override
	public void decode(ParserObj parserObj) {
		ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		DWord eLfanew=new DWord(codeLst,0x3c);
		//String hexCode=eLfanew.toHexString();
		DosHeader dosHeader=new DosHeader();
		dosHeader.setE_lfanew((int)eLfanew.getCodeNumber());
		parserObj.addMemoryBean(dosHeader);
		//logger.info(hexCode);
	}
	
}
