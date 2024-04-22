package peload.bean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import util.Loader;

@XmlRootElement
public class ParserBeanLst {
	@XmlElement(name = "parserbean")
	List<ParserBean> parserBeanLst;

	 
	 public ParserBeanLst() {
		 parserBeanLst=new ArrayList<ParserBean>();
	  }
	public List<ParserBean> getParserBeanLst() {
		return parserBeanLst;
	}
	 
	
	public void addParserBean(ParserBean bean){
		parserBeanLst.add(bean);
	}
	
	private void test1()throws JAXBException, IOException{
		   JAXBContext context = JAXBContext.newInstance(ParserBeanLst.class);
			// 下面代码演示将对象转变为xml
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
			ParserBean parserBean=new ParserBean("MsDosHeaderParser");
			ParserBean parserBean2=new ParserBean("PeHeaderParser");
			ParserBeanLst lst=new ParserBeanLst();
			lst.addParserBean(parserBean);
			lst.addParserBean(parserBean2);
			
			String resourcePath = Loader.getResourcePathByClazz(ParserBeanLst.class);
			String path = new File(resourcePath).getParentFile().getParentFile().getAbsolutePath() + "/res/";
			path="test2.xml";
	     	FileWriter fw = new FileWriter(path);
	     	marshaller.marshal(lst, fw); 
	   }
	 
	public static void main(String[] args) throws JAXBException, IOException {
		ParserBeanLst l=new ParserBeanLst();
		l.test1();
	}
	
}
