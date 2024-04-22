package cpu.operand.impl;

import cpu.operand.Operand;
import cpu.operand.bean.OperandContextBean;
import cpu.parent.CpuObject;
import cpu.register.SegementRegister;

public class SegementRegisterOperand extends Operand{
	public SegementRegisterOperand(CpuObject parameterObj) {
		super(parameterObj);
	}


	private SegementRegister segReg;
	
	
    @Override
	public void setOperandContextBean(OperandContextBean contextBean) {
    	segReg=contextBean.getSegementRegister();
	}

    
    public String toString(){
    	return segReg.getRegisterName();
    } 

}
