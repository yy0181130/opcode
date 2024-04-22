package cpu.bean;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import cpu.parent.CpuBeanObj;

@XmlRootElement
public class OneByteOpcodeBean extends CpuBeanObj implements Cloneable{

	@XmlAttribute(required = false)
	protected Boolean isPrefix;
	@XmlAttribute(required = false)
	protected Boolean isGrp;
	
	public OneByteOpcodeBean clone() {  
	    OneByteOpcodeBean o = null;  
        try {  
            o = (OneByteOpcodeBean) super.clone();  
        } catch (CloneNotSupportedException ex) {  
            ex.printStackTrace();  
        }  
       return o;  
   }  
	
	public String getOpcode() {
		return opcode;
	}

	public int getLength() {
		return length;
	}

	public String getOpcodeName() {
		return opcodeName;
	}

	public String getFirstOperandType() {
		return firstOperandType;
	}

	public String getSecondOperandType() {
		return secondOperandType;
	}

	public boolean isPrefix() {
		return isPrefix;
	}

	public boolean isGrp() {
		return isGrp;
	}
	
	public String getHandler() {
		return handler;
	}

	public OneByteOpcodeBean() {
	}

	public OneByteOpcodeBean(String opcode, String opcodeName,
			String firstOperandType, String secondOperandType,
			boolean isPrefix, boolean isGrp) {

		this.opcode = opcode;
		this.opcodeName = opcodeName;
		this.firstOperandType = firstOperandType;
		this.secondOperandType = secondOperandType;
		this.isPrefix = isPrefix;
		this.isGrp = isGrp;
	}

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(OneByteOpcodeBean.class);
		// 下面代码演示将对象转变为xml
		Marshaller m = context.createMarshaller();
		OneByteOpcodeBean user = new OneByteOpcodeBean("0F", "ADD", "gb", "ac",
				true, true);
		FileWriter fw = new FileWriter("test.xml");
		m.marshal(user, fw);
	}

}
