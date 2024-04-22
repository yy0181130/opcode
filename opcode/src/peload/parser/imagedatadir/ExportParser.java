package peload.parser.imagedatadir;

import org.springframework.stereotype.Service;

import peload.headerbean.OptionalHeader;
import peload.headerbean.imagedatadirbean.ImageDataDirectoryBean;
import peload.parent.ParserObj;

/**
 * 输出表解析
 * @author :youy
 * @time:2014年5月13日
 */
@Service
public class ExportParser extends ParserObj{
	
	@Override
	public void decode(ParserObj parserObj) {
		//ArrayList<Integer>  codeLst=parserObj.getPefilelst();
		OptionalHeader optionalHeader = (OptionalHeader) parserObj.getParameter(OptionalHeader.class.getSimpleName());
		ImageDataDirectoryBean bean=optionalHeader.getLst().get(0);
		//System.out.println(bean.getVirtualAddress()+";");
	}
   
}
