package cpu.bean;

import java.util.List;

import cpu.operand.bean.EffectiveOperandSize;
import cpu.parent.CpuBeanObj;
import cpu.register.SegementRegister;

/**
 * 和当前指令相关的bean参数信息
 * @author youy
 *
 */
public class InstructParserBean extends CpuBeanObj {

	private int currentIndex;// 当前的指令位置
	private boolean manulSetEip;// 是否手动设置Eip;
	private EffectiveOperandSize effectiveOperandSize=new EffectiveOperandSize();//操作数有效位默认32
	private SegementRegister  segementRegister;//设置段寄存器
	private List<Integer> frameLst;
	private boolean isRepFlag;//是否是rep;rep指令的目的是重复其上面的指令.ECX的值是重复的次数.
	private boolean isRepneFlag;
	
	public boolean isRepneFlag() {
		return isRepneFlag;
	}


	public void setRepneFlag(boolean isRepneFlag) {
		this.isRepneFlag = isRepneFlag;
	}


	public boolean isRepFlag() {
        return isRepFlag;
    }

    
    public void setRepFlag(boolean isRepFlag) {
        this.isRepFlag = isRepFlag;
    }

    public SegementRegister getSegementRegister() {
		return segementRegister;
	}

	public void setSegementRegister(SegementRegister segementRegister) {
		this.segementRegister = segementRegister;
	}

	public EffectiveOperandSize getEffectiveOperandSize() {
		return effectiveOperandSize;
	}

	public void setEffectiveOperandSize(EffectiveOperandSize effectiveOperandSize) {
		this.effectiveOperandSize = effectiveOperandSize;
	}

	public List<Integer> getFrameLst() {
		return frameLst;
	}

	public void setFrameLst(List<Integer> frameLst) {
		this.frameLst = frameLst;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public boolean isManulSetEip() {
		return manulSetEip;
	}

	public void setManulSetEip(boolean manulSetEip) {
		this.manulSetEip = manulSetEip;
	}



}
