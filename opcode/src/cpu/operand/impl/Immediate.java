package cpu.operand.impl;

import structs.operator.IOperatorNum;
import cpu.operand.Operand;
import cpu.operand.bean.OperandContextBean;
import cpu.parent.CpuObject;

public class Immediate extends Operand{
    public Immediate(CpuObject parameterObj) {
		super(parameterObj);
	}


	private IOperatorNum immediate;
	
	@Override
	public void setOperandContextBean(OperandContextBean contextBean) {
		immediate=contextBean.getImmediate();
	}

	@Override
	public String toString() {
		return immediate.toHexString();
	}
	
	public Integer toDecValue(){
		return immediate.toDecValue();
	}
	
	public IOperatorNum getImmediate(){
		return immediate;
	}
	
}
