package peload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import peload.bean.ParserBean;
import peload.bean.ParserBeanLst;
import peload.headerbean.OptionalHeader;
import peload.headerbean.SectionTableHeader;
import peload.headerbean.SectionTableHeaderLst;
import peload.headerbean.imagedatadirbean.componet.ImageThunkData32Bean;
import peload.headerbean.imagedatadirbean.imageimport.ImageImportDescriptorBean;
import peload.parent.ParserObj;
import util.GetACT;
import util.Loader;

public class ParserEngine extends ParserObj{
	private static ParserBeanLst parserObjLst;
	static{
		getResContext();
	}
	
	private static void getResContext(){
		String resourcePath = Loader.getResourcePathByClazz(ParserEngine.class);
  		String path = new File(resourcePath).getParentFile().getAbsolutePath() + "/res/parser.xml";
  	   try {
  				JAXBContext context = JAXBContext.newInstance(ParserBeanLst.class);
  				File file = new File(path);//用于输出的XML文档
  				Marshaller marshaller = context.createMarshaller();//创建编组
  				ParserBeanLst lst = (ParserBeanLst) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
  				parserObjLst=lst;
  				
  		   } catch (JAXBException e) {
  				e.printStackTrace();
  			  }
	}
	
	@Override
	public void decode(ParserObj parserObj) {
       List<ParserBean> parserBeanLst= parserObjLst.getParserBeanLst();
	   for (ParserBean parserBean : parserBeanLst) {
		 String parserName= parserBean.getParserName();
		 ParserObj obj=(ParserObj) GetACT.getApplicationContext().getBean(parserName);  
		 obj.decode(parserObj);
		}
	 }
	
	
	private void test_LoadPeSections() {
		ArrayList<Integer> codeLst = this.getPefilelst();
		SectionTableHeaderLst sectionTHL = (SectionTableHeaderLst) this.getParameter(SectionTableHeaderLst.class.getSimpleName());
		
		List<SectionTableHeader> sectionTableLst = sectionTHL.getSectionTableLst();
		for (SectionTableHeader sectionTableHeader : sectionTableLst) {
			System.out.println("name:"+sectionTableHeader.getName().getValueStr()+";virtualAddress:"+sectionTableHeader.getVirtualAddress().toHexString());
		}
	}
	
	private void test_getImportTable(){
		OptionalHeader optionalHeader = (OptionalHeader)getParameter(OptionalHeader.class.getSimpleName());
		List<ImageImportDescriptorBean> lst=optionalHeader.getImportDesBeanLst();
		for (ImageImportDescriptorBean imageImportDescriptorBean : lst) {
			System.out.println(imageImportDescriptorBean.getNameValue());
			List<ImageThunkData32Bean> medLst =imageImportDescriptorBean.getMedLst();
			for (ImageThunkData32Bean imageThunkData32Bean : medLst) {
				System.out.println(imageThunkData32Bean.getAsciiName()+";"+imageThunkData32Bean.getVirtualAddress().toHexString());
			}
			System.out.println();
		}
	}
	
   
   
	 public static void main(String[] args) throws IOException {
		 ParserEngine l=new ParserEngine();
		  //String path="F:\\vm\\cpu\\是男人就撑过20秒.exe";
		   //String path="G:\\cpu\\是男人就撑过20秒.exe";
		 // String path="G:\\redmoon\\redmoon38\\RMC.exe";
		 //String path="G:\\redmoon38\\RMC.exe";
		 String path="F:\\redmoongw\\Redmoon\\Redmoon.exe";
		 l.loadPeFile(path);
	     l.decode(l);
	     l.test_getImportTable();
		 //l.doLoadPeSections();
	 }
	
}
