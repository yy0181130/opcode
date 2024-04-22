package cpu.operand.bean;

import cpu.register.OperandSize;

public class EffectiveOperandSize {
    private OperandSize effectiveSize;

	public OperandSize getEffectiveSize() {
		return effectiveSize;
	}

	public void setEffectiveSize(OperandSize effectiveSize) {
		this.effectiveSize = effectiveSize;
	}
    
	public EffectiveOperandSize() {
		effectiveSize=OperandSize.dwordptr;
	}
 }
