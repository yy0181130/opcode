package cpu.dump;

import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import cpu.dump.bean.DumpBean;
import cpu.init.CpuRegInit;
import cpu.register.Register;

public class DumpService {

	   public void dumpWrite() {
		   try{
				JAXBContext context = JAXBContext.newInstance(DumpBean.class);
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);// 设置编组属性，使得输出的XML文档进行格式化（提供缩进）
				
				String eaxValue=Register.eax.getValue().toHexString();
				String ecxValue=Register.ecx.getValue().toHexString();
				String edxValue=Register.edx.getValue().toHexString();
				String ebxValue=Register.ebx.getValue().toHexString();
				String espValue=Register.esp.getValue().toHexString();
				String ebpValue=Register.ebp.getValue().toHexString();
				String esiValue=Register.esi.getValue().toHexString();
				String ediValue=Register.edi.getValue().toHexString();
				DumpBean dumpBean=new DumpBean(eaxValue,ecxValue,edxValue,ebxValue,espValue,ebpValue,esiValue,ediValue);
				FileWriter fw = new FileWriter("dump.xml");
				marshaller.marshal(dumpBean, fw);
		    }catch(Exception ex){
			}
	 }
	   
	   private void test(){
		   CpuRegInit init=new CpuRegInit();
	       init.initValue();
	       dumpWrite();
	   }
	   
	   public static void main(String[] args) {
		   DumpService d=new DumpService();
		   d.test();
	  }
	
	
}
