package cpu.instructions;

import org.springframework.stereotype.Component;

import structs.DWord;
import structs.operator.IOperatorNum;
import util.GetACT;
import cpu.aid.OperandIo;
import cpu.instructParent.AddParent;
import cpu.operand.Operand;
import cpu.operand.impl.Immediate;
import cpu.operand.impl.RegisterOperand;
import cpu.parent.CpuObject;
import cpu.util.HexCalculationUtil;

@Component
public class Add extends AddParent {
    @Override
    public void doOpcode(CpuObject parameterObj) {
        if(!isDoOpcode())return;
    	OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
    	Operand operand1=operandIo.getFirstOperandType(parameterObj);
    	Operand operand2=operandIo.getSecondOperandType(parameterObj);
    	if(operand1 instanceof RegisterOperand && operand2 instanceof Immediate){
    		addRegImm(parameterObj,operand1,operand2);
    	}
    }
    
    private void addRegImm(CpuObject parameterObj,Operand operand1,Operand operand2){
    	RegisterOperand reg=(RegisterOperand)operand1;
    	Immediate imm=(Immediate)operand2;
    	IOperatorNum num=imm.getImmediate();
    	IOperatorNum value1=reg.getRegister().getValue();
    	String value=HexCalculationUtil.addressAddInt(value1, num);
    	DWord valueOfReg=new DWord(value);
    	reg.getRegister().setValue(valueOfReg);
     }
    
    
    
}
