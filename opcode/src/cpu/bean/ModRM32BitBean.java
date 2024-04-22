package cpu.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"xmm","mm","r16","r8","r32", "effectiveAddress", "rm", "reg", "mod","hexadecimal"})
public class ModRM32BitBean {
	@XmlAttribute
	protected String hexadecimal;
	@XmlAttribute
	protected String mod;
	@XmlAttribute
	protected String reg;
	@XmlAttribute
	protected String rm;
	@XmlAttribute
	protected String effectiveAddress;
	@XmlAttribute
	protected String r32;
	@XmlAttribute(required=false)
	protected String r8;
	@XmlAttribute(required=false)
	protected String r16;
	@XmlAttribute(required=false)
	protected String mm;
	@XmlAttribute(required=false)
	protected String xmm;
	
	
	public void setValues(String r8,String r16,String mm,String xmm){
		this.r8=r8;
		this.r16=r16;
		this.mm=mm;
		this.xmm=xmm;
	}
	public String getR16() {
		return r16;
	}
	public String getMm() {
		return mm;
	}
	public String getXmm() {
		return xmm;
	}
	public String getR8() {
		return r8;
	}
	public ModRM32BitBean() {
	}
	public ModRM32BitBean(String hexadecimal, String mod, String reg,
			String rm, String effectiveAddress, String r32) {
		this.hexadecimal = hexadecimal;
		this.mod = mod;
		this.reg = reg;
		this.rm = rm;
		this.effectiveAddress = effectiveAddress;
		this.r32 = r32;
	}
	public String getHexadecimal() {
		return hexadecimal;
	}
	public String getMod() {
		return mod;
	}
	public String getReg() {
		return reg;
	}
	public String getRm() {
		return rm;
	}
	public String getEffectiveAddress() {
		return effectiveAddress;
	}
	public String getR32() {
		return r32;
	}
	
}
