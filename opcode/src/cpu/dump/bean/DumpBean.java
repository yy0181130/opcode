package cpu.dump.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "eax", "ecx", "edx","ebx","esp","ebp","esi","edi"})
public class DumpBean {
	@XmlElement
	protected String eax;
	@XmlElement
	protected String ecx;
	@XmlElement
	protected String edx;
	@XmlElement
	protected String ebx;
	@XmlElement
	protected String esp;
	@XmlElement
	protected String ebp;
	@XmlElement
	protected String esi;
	@XmlElement
	protected String edi;
	
	public DumpBean(String eax, String ecx, String edx, String ebx, String esp,
			String ebp, String esi, String edi) {
		super();
		this.eax = eax;
		this.ecx = ecx;
		this.edx = edx;
		this.ebx = ebx;
		this.esp = esp;
		this.ebp = ebp;
		this.esi = esi;
		this.edi = edi;
	}

	public DumpBean() {
	}

	public String getEax() {
		return eax;
	}

	public String getEcx() {
		return ecx;
	}

	public String getEdx() {
		return edx;
	}

	public String getEbx() {
		return ebx;
	}

	public String getEsp() {
		return esp;
	}

	public String getEbp() {
		return ebp;
	}

	public String getEsi() {
		return esi;
	}

	public String getEdi() {
		return edi;
	}
	
	
	
	
	
}
