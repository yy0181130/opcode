package cpu.parent;

import javax.xml.bind.annotation.XmlAttribute;

public class CpuBeanObj {
	@XmlAttribute(required = true)
	protected String opcode;

	@XmlAttribute(required = true)
	protected String opcodeName;
	
	
	@XmlAttribute(required = false) 
	protected int length;
	
	@XmlAttribute
	protected String handler;
	
    @XmlAttribute(required = false)
	protected String firstOperandType;
	@XmlAttribute(required = false)
	protected String secondOperandType;
	public String getOpcode() {
		return opcode;
	}
	public String getOpcodeName() {
		return opcodeName;
	}
	public int getLength() {
		return length;
	}
	public String getHandler() {
		return handler;
	}
	public String getFirstOperandType() {
		return firstOperandType;
	}
	public String getSecondOperandType() {
		return secondOperandType;
	}
	
	public void modifyFirstOperandType(String firstOperandType){
		this.firstOperandType=firstOperandType;
	}
	public void modifySecondOperandType(String secondOperandType){
		this.secondOperandType=secondOperandType;
	}
	
	
	
	
}
