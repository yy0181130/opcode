package peload.parser;

import org.springframework.stereotype.Service;

import peload.headerbean.DosHeader;
import peload.headerbean.PeHeader;
import peload.parent.ParserObj;
import structs.DWord;

@Service
public class PeHeaderParser extends ParserObj{
	
	@Override
	public void decode(ParserObj parserObj) {
		//ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		DosHeader dosHeader = (DosHeader) parserObj.getParameter(DosHeader.class.getSimpleName());
		int peHaderIndex=dosHeader.getE_lfanew();
		int endIndex=peHaderIndex+DWord.getSize();
		PeHeader peHeader=new PeHeader();
		peHeader.setPeHaderIndex(peHaderIndex);
		peHeader.setEndPeHeaderIndex(endIndex);
		parserObj.addMemoryBean(peHeader);
	}
   
}
