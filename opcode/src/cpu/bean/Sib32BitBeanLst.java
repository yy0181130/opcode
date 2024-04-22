package cpu.bean;

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
import cpu.CpuEngine;

@XmlRootElement(name = "Root")
public class Sib32BitBeanLst {
	
	@XmlElement(name = "item")
	List<Sib32BitBean> sib32BitBeanLst;

	
	public List<Sib32BitBean> getSib32BitBeanLst() {
		return sib32BitBeanLst;
	}

	public Sib32BitBeanLst() {
		sib32BitBeanLst=new ArrayList<Sib32BitBean>();
	}
	
	public void addSib32Bean(Sib32BitBean sib32BitBean){
		sib32BitBeanLst.add(sib32BitBean);
	}
	
	
	private void test_1() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Sib32BitBeanLst.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		Sib32BitBean bean1 = new Sib32BitBean("08","00","000","001","[ecx]","ecx");
		Sib32BitBean bean2 = new Sib32BitBean("10","00","010","000","[edx]","edx");
		Sib32BitBeanLst lst = new Sib32BitBeanLst();
	    lst.addSib32Bean(bean1);
		lst.addSib32Bean(bean2);
       FileWriter fw = new FileWriter("test2.xml");
		marshaller.marshal(lst, fw);
	}
	
	   private void test_2()throws JAXBException, IOException{
		   JAXBContext context = JAXBContext.newInstance(Sib32BitBeanLst.class);
			String resourcePath = Loader.getResourcePathByClazz(CpuEngine.class);
	  		String path = new File(resourcePath).getParentFile().getAbsolutePath() + "/res/sib32bit.xml";
		   File file = new File(path);//用于输出的XML文档
		   Sib32BitBeanLst m=new Sib32BitBeanLst();
		   Marshaller marshaller = context.createMarshaller();//创建编组
		   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		   Sib32BitBeanLst mLst = (Sib32BitBeanLst) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
	      System.out.println(mLst.getSib32BitBeanLst().get(1).getR32());
	   }
	
	
	public static void main(String[] args) throws JAXBException, IOException {
		Sib32BitBeanLst m=new Sib32BitBeanLst();
		m.test_2();
	}
	
}
