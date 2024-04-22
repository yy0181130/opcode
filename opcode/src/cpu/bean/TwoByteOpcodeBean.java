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
public class TwoByteOpcodeBean extends CpuBeanObj{
	
	@XmlAttribute(required = false)
	protected String pfx;

	public TwoByteOpcodeBean() {
	}

	public TwoByteOpcodeBean(String opcode, String opcodeName, String handler,
			String firstOperandType, String secondOperandType, String pfx,
			int length) {
		this.opcode = opcode;
		this.opcodeName = opcodeName;
		this.handler = handler;
		this.firstOperandType = firstOperandType;
		this.secondOperandType = secondOperandType;
		this.pfx = pfx;
		this.length = length;
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

	
	
	public String getHandler() {
		return handler;
	}


	

	public String getPfx() {
		return pfx;
	}
	
	
	

	public static void main(String[] args) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(TwoByteOpcodeBean.class);
		// 下面代码演示将对象转变为xml
		Marshaller m = context.createMarshaller();
		TwoByteOpcodeBean user = new TwoByteOpcodeBean("b6", "movzx","handle", "Gv", "Eb","3", 1);
		FileWriter fw = new FileWriter("test.xml");
		m.marshal(user, fw);
	}

}
