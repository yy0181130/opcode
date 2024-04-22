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
public class ModRM32BitBeanLst {
	
	@XmlElement(name = "item")
	List<ModRM32BitBean> modRm32BitBeanLst;

	public List<ModRM32BitBean> getModRm32BitBeanLst() {
		return modRm32BitBeanLst;
	}
	
	public ModRM32BitBeanLst() {
		modRm32BitBeanLst=new ArrayList<ModRM32BitBean>();
	}
	
	public void addModRm32Bean(ModRM32BitBean m){
		modRm32BitBeanLst.add(m);
	}
	
	
	private void test_1() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(ModRM32BitBeanLst.class);
		// 下面代码演示将对象转变为xml
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		ModRM32BitBean bean1 = new ModRM32BitBean("08","00","001","000","[eax]","ecx");
		ModRM32BitBean bean2 = new ModRM32BitBean("10","00","010","000","[eax]","edx");
		ModRM32BitBeanLst lst = new ModRM32BitBeanLst();
	    lst.addModRm32Bean(bean1);
		lst.addModRm32Bean(bean2);
        FileWriter fw = new FileWriter("test2.xml");
		marshaller.marshal(lst, fw);
	}
	
	   private void test_2()throws JAXBException, IOException{
		   JAXBContext context = JAXBContext.newInstance(ModRM32BitBeanLst.class);
			String resourcePath = Loader.getResourcePathByClazz(CpuEngine.class);
	  		String path = new File(resourcePath).getParentFile().getAbsolutePath() + "/res/modrm32bit.xml";
		   File file = new File(path);//用于输出的XML文档
		   ModRM32BitBeanLst m=new ModRM32BitBeanLst();
		   Marshaller marshaller = context.createMarshaller();//创建编组
		   marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		   ModRM32BitBeanLst mLst = (ModRM32BitBeanLst) context.createUnmarshaller().unmarshal(file);//从文件解组到一个新对象中
	       System.out.println(mLst.getModRm32BitBeanLst().get(1).getEffectiveAddress());
	   }
	
	   private void test_3() throws IOException, JAXBException{
		   ModRM32BitBeanLst mod32BitBeanLst=(ModRM32BitBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"modrm32bit.xml",ModRM32BitBeanLst.class);
		   List<ModRM32BitBean> bitBeanLst= mod32BitBeanLst.getModRm32BitBeanLst();
		   for (ModRM32BitBean modRM32BitBean : bitBeanLst) {
			 String r32=  modRM32BitBean.getR32();
			 if(r32.equals("eax"))
				 modRM32BitBean.setValues("al", "ax", "mm0", "xmm0");
			else if(r32.equals("ecx"))
				 modRM32BitBean.setValues("cl", "cx", "mm1", "xmm1");
			else if(r32.equals("edx"))
				 modRM32BitBean.setValues("dl", "dx", "mm2", "xmm2");
			else if(r32.equals("ebx"))
				 modRM32BitBean.setValues("bl", "bx", "mm3", "xmm3");
			else if(r32.equals("esp"))
				 modRM32BitBean.setValues("ah", "sp", "mm4", "xmm4");
			else if(r32.equals("ebp"))
				 modRM32BitBean.setValues("ch", "bp", "mm5", "xmm5");
			else if(r32.equals("esi"))
				 modRM32BitBean.setValues("dh", "si", "mm6", "xmm6");
			else if(r32.equals("edi"))
				 modRM32BitBean.setValues("bh", "di", "mm7", "xmm7");
		}
		    FileWriter fw = new FileWriter("test2.xml");
		    
			JAXBContext context = JAXBContext.newInstance(ModRM32BitBeanLst.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
		    marshaller.marshal(mod32BitBeanLst, fw);
	}
	   
	   
	
	public static void main(String[] args) throws JAXBException, IOException {
		ModRM32BitBeanLst m=new ModRM32BitBeanLst();
		m.test_3();
	}
	
}
