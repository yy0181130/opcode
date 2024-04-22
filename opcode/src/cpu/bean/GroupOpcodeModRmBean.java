package cpu.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "instruct", "encode" })
public class GroupOpcodeModRmBean {
	@XmlAttribute
	private String encode;
	@XmlAttribute
	private String instruct;
	
    @XmlAttribute(required = false)
	protected String firstOperandType;
	@XmlAttribute(required = false)
	protected String secondOperandType;
	
	public GroupOpcodeModRmBean() {
	}

	public GroupOpcodeModRmBean(String encode, String instruct) {
		this.encode = encode;
		this.instruct = instruct;
	}

	public String getEncode() {
		return encode;
	}

	public String getInstruct() {
		return instruct;
	}

	public String getFirstOperandType() {
		return firstOperandType;
	}

	public String getSecondOperandType() {
		return secondOperandType;
	}

}
