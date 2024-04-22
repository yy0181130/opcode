package cpu.util.bean;

public class ModRMUBean {
	private String r32;
	private String r16;
	private String r8;
	private String mm;
	private String xmm;

	public ModRMUBean(String r32, String r16, String r8, String mm, String xmm) {
		this.r32 = r32;
		this.r16 = r16;
		this.r8 = r8;
		this.mm = mm;
		this.xmm = xmm;
	}

	public String getR32() {
		return r32;
	}

	public void setR32(String r32) {
		this.r32 = r32;
	}

	public String getR16() {
		return r16;
	}

	public void setR16(String r16) {
		this.r16 = r16;
	}

	public String getR8() {
		return r8;
	}

	public void setR8(String r8) {
		this.r8 = r8;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getXmm() {
		return xmm;
	}

	public void setXmm(String xmm) {
		this.xmm = xmm;
	}
}
