package cpu.instructions;

import org.springframework.stereotype.Component;

import structs.DWord;
import util.GetACT;
import cpu.aid.OperandIo;
import cpu.bean.SwithTableBean;
import cpu.instructParent.CmpParent;
import cpu.operand.Operand;
import cpu.operand.choose.OperandType;
import cpu.operand.impl.RegisterOperand;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

@Component
public class Cmp extends CmpParent{
	
    @Override
    public void doOpcode(CpuObject parameterObj) {
    	doSwithTable(parameterObj);
    }
    
    
    private void doSwithTable(CpuObject parameterObj){
    	OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
    	Operand operand=operandIo.getFirstOperandType(parameterObj);
    	if(!(operand.getOperandType()==OperandType.RegisterType))return;
    	Operand operandS=operandIo.getSecondOperandType(parameterObj);
    	if(!(operandS.getOperandType()==OperandType.ImmediateType))return;
    	
    	CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
    	SwithTableBean swithBean=cpuStatus.getSwithTableBean();
    	swithBean.setSwicthtableLength(operandS.toDecValue());
    	DWord ip=new DWord(parameterObj.getCpuStatus().GetEipDWord().toHexString());
    	swithBean.setCmpIPPosation(ip);
    	
    	RegisterOperand reg=(RegisterOperand)operand;
    	swithBean.setCmpRegName(reg.toString());
    }
    
}
