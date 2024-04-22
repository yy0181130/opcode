package cpu.bean;

import util.HexCodeUtil;
import cpu.parent.CpuBeanObj;

public class ModRMBean extends CpuBeanObj{
	private String mod;
	private String rm;
	private String reg;
    private Integer modrmValue;
	
	public ModRMBean() {
	}

	public Integer getModrmValue() {
		return modrmValue;
	}



	public void setModrmValue(Integer modrmValue) {
		this.modrmValue = modrmValue;
	}



	public ModRMBean(String mod, String reg,String rm) {
		this.mod = mod;
		this.rm = rm;
		this.reg = reg;
	}

	public String getMod() {
		return mod;
	}

	public void setMod(String mod) {
		this.mod = mod;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}
	
	public String toString(){
		String hexValue=HexCodeUtil.getHexCodeByInt(modrmValue);
		return "mod:"+mod+";reg:"+reg+";rm:"+rm+";modrmValue:"+hexValue;
	}
		

}
