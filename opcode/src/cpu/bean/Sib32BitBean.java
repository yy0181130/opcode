package cpu.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlType(propOrder = {"r32", "sacledIndex", "index", "base", "ss","hexadecimal"})
public class Sib32BitBean {
	@XmlAttribute
	protected String hexadecimal;
	@XmlAttribute
	protected String ss;
	@XmlAttribute
	protected String base;
	@XmlAttribute
	protected String index;
	@XmlAttribute
	protected String sacledIndex;
	@XmlAttribute
	protected String r32;
	
	
	public Sib32BitBean() {
	}

	public Sib32BitBean(String hexadecimal, String ss, String base,
			String index, String sacledIndex, String r32) {
		super();
		this.hexadecimal = hexadecimal;
		this.ss = ss;
		this.base = base;
		this.index = index;
		this.sacledIndex = sacledIndex;
		this.r32 = r32;
	}



	public String getHexadecimal() {
		return hexadecimal;
	}


	public String getSs() {
		return ss;
	}


	public String getBase() {
		return base;
	}


	public String getIndex() {
		return index;
	}


	public String getSacledIndex() {
		return sacledIndex;
	}


	public String getR32() {
		return r32;
	}
	
	
}
