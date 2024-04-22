package cpu.bean;

import cpu.parent.CpuBeanObj;

public class SibBean extends CpuBeanObj{
	private String ss;
	private String base;
	private String index;
    private Integer hexCode;
    
    public SibBean() {
	}
    
	public SibBean(String ss, String base, String index, Integer hexCode) {
		this.ss = ss;
		this.base = base;
		this.index = index;
		this.hexCode = hexCode;
	}
	public String getSs() {
		return ss;
	}
	public void setSs(String ss) {
		this.ss = ss;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public Integer getHexCode() {
		return hexCode;
	}
	public void setHexCode(Integer hexCode) {
		this.hexCode = hexCode;
	}
	
	

		

}
