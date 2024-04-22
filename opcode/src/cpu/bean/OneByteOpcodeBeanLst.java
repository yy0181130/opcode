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

@XmlRootElement
public class OneByteOpcodeBeanLst {

	 @XmlElement(name = "opcode")
	 List<OneByteOpcodeBean> opcodeLst;
	 
	public List<OneByteOpcodeBean> getOpcodeLst() {
		return opcodeLst;
	}

	public OneByteOpcodeBeanLst() {
		opcodeLst = new ArrayList<OneByteOpcodeBean>();
	}

   public void addOpcode(OneByteOpcodeBean opcodeBean){
	   opcodeLst.add(opcodeBean);
    }	
	
   
   private void test1()throws JAXBException, IOException{
	   JAXBContext context = JAXBContext.newInstance(OneByteOpcodeBeanLst.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		OneByteOpcodeBean one1 = new OneByteOpcodeBean("ff", "ADD", "gb", "ac",true, true);
		OneByteOpcodeBean one2 = new OneByteOpcodeBean("fe", "ADD", "gb", "ac",true, true);
		OneByteOpcodeBeanLst lst=new OneByteOpcodeBeanLst();
		lst.addOpcode(one1);
		lst.addOpcode(one2);
		
		String resourcePath = Loader.getResourcePathByClazz(OneByteOpcodeBeanLst.class);
		String path = new File(resourcePath).getParentFile().getParentFile().getAbsolutePath() + "/res/";
		path="test2.xml";
     	FileWriter fw = new FileWriter(path);
     	marshaller.marshal(lst, fw); 
   }
   
   private void test2()throws JAXBException, IOException{
	   JAXBContext context = JAXBContext.newInstance(OneByteOpcodeBeanLst.class);
	   File file = new File("test2.xml");//用于输出的XML文档
	   OneByteOpcodeBeanLst oneByteOpcodeLst=new OneByteOpcodeBeanLst();
	   Marshaller marshaller = context.createMarshaller();//创建编组
	   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
	   OneByteOpcodeBeanLst oneLst = (OneByteOpcodeBeanLst) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
       System.out.println(oneLst.getOpcodeLst().get(1).getOpcode());
   }
   
   private void test3()throws JAXBException, IOException{
	   JAXBContext context = JAXBContext.newInstance(OneByteOpcodeBeanLst.class);
	   File file = new File("F:/vm/CODE/opcode/src/cpu/res/OneByteOpcode.xml");//用于输出的XML文档
	   OneByteOpcodeBeanLst oneByteOpcodeLst=new OneByteOpcodeBeanLst();
	   Marshaller marshaller = context.createMarshaller();//创建编组
	   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
	   OneByteOpcodeBeanLst oneLst = (OneByteOpcodeBeanLst) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
       System.out.println(oneLst.getOpcodeLst().get(0).getOpcode());
   }
   	
   
   
	public static void main(String[] args) throws JAXBException, IOException {
		OneByteOpcodeBeanLst o=new OneByteOpcodeBeanLst();
		o.test3();
	}

}
