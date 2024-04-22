package cpu.bean;

import java.util.ArrayList;
import java.util.List;

import cpu.parent.CpuBeanObj;

public class PrefixBean extends CpuBeanObj {
	private boolean isHavePrefix;
	private List<Integer> prefixLst;
	private boolean isHaveOperandSizeOverride;// 66H
	private boolean isHaveRep;// 66H
	
	
	public PrefixBean() {
		prefixLst=new ArrayList<Integer>();
	}
	
	
	public void addPrefix(Integer code){
		prefixLst.add(code);
		switch(code){
		case 0x66:
			isHaveOperandSizeOverride=true;break;
		case 0xf3:
			isHaveRep=true;break;
		}
	}
	public boolean isHavePrefix() {
		return isHavePrefix;
	}

	public void setHavePrefix(boolean isHavePrefix) {
		this.isHavePrefix = isHavePrefix;
	}

	public List<Integer> getPrefixLst() {
		return prefixLst;
	}

	public void setPrefixLst(List<Integer> prefixLst) {
		this.prefixLst = prefixLst;
	}

	public boolean isHaveOperandSizeOverride() {
		return isHaveOperandSizeOverride;
	}

	public void setHaveOperandSizeOverride(boolean isHaveOperandSizeOverride) {
		this.isHaveOperandSizeOverride = isHaveOperandSizeOverride;
	}


	public boolean isHaveRep() {
		return isHaveRep;
	}

}
