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

import cpu.CpuEngine;

import util.Loader;

@XmlRootElement
public class TwoByteOpcodeBeanLst {

	 @XmlElement(name = "opcode")
	 List<TwoByteOpcodeBean> twoByteOpcodeLst;
	 
	
	public List<TwoByteOpcodeBean> getTwoByteOpcodeLst() {
		return twoByteOpcodeLst;
	}

	public TwoByteOpcodeBeanLst() {
		twoByteOpcodeLst = new ArrayList<TwoByteOpcodeBean>();
	}

   public void addOpcode(TwoByteOpcodeBean twoByteOpcodeBean){
	   twoByteOpcodeLst.add(twoByteOpcodeBean);
    }	
	
   
   private void test1()throws JAXBException, IOException{
	   JAXBContext context = JAXBContext.newInstance(TwoByteOpcodeBeanLst.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		TwoByteOpcodeBean t1 = new TwoByteOpcodeBean("b6", "movzx","handle", "Gv", "Eb","3", 1);
		TwoByteOpcodeBean t2 = new TwoByteOpcodeBean("b7", "movzx","handle", "Gv", "Ew","3", 1);
		TwoByteOpcodeBeanLst lst=new TwoByteOpcodeBeanLst();
		lst.addOpcode(t1);
		lst.addOpcode(t2);
		
		String resourcePath = Loader.getResourcePathByClazz(TwoByteOpcodeBeanLst.class);
		String path = new File(resourcePath).getParentFile().getParentFile().getAbsolutePath() + "/res/";
		path="test2.xml";
     	FileWriter fw = new FileWriter(path);
     	marshaller.marshal(lst, fw); 
   }
   
   private void test2()throws JAXBException, IOException{
	   TwoByteOpcodeBeanLst twoLst =(TwoByteOpcodeBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"twoByteOpcode.xml",TwoByteOpcodeBeanLst.class);
	   List<TwoByteOpcodeBean> twoBeanLst= twoLst.getTwoByteOpcodeLst();
	   for (TwoByteOpcodeBean twoByteOpcodeBean : twoBeanLst) {
		   System.out.println(twoByteOpcodeBean.getOpcodeName());   
	   }
   }
   	
   
   
	public static void main(String[] args) throws JAXBException, IOException {
		TwoByteOpcodeBeanLst o=new TwoByteOpcodeBeanLst();
		o.test2();
	}

}
