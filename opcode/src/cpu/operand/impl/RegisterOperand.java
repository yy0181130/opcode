package cpu.operand.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpu.operand.Operand;
import cpu.operand.bean.OperandContextBean;
import cpu.parent.CpuObject;
import cpu.register.Register;

public class RegisterOperand extends Operand{
	private static Logger logger = LogManager.getLogger(RegisterOperand.class);
	
	public RegisterOperand(CpuObject parameterObj) {
		super(parameterObj);
	}


	private Register register;
	
	
    @Override
	public void setOperandContextBean(OperandContextBean contextBean) {
    	register=contextBean.getRegister();
    	//logger.info(" operand:"+register.getRegisterName());
	}

    
    public String toString(){
    	return register.getRegisterName();
    } 

    public Register getRegister(){
    	return register;
    }
    
    public int getRegSize(){
        return register.getRegSize();
    }
    
    
}
