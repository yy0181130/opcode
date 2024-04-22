package cpu.instructions;

import org.springframework.stereotype.Component;

import structs.DWord;
import structs.operator.IOperatorNum;
import util.GetACT;
import cpu.aid.OperandIo;
import cpu.instructParent.PushParent;
import cpu.operand.Operand;
import cpu.operand.impl.Immediate;
import cpu.parent.CpuObject;
import cpu.stack.CpuStack;


@Component
public class Push extends PushParent{
	 @Override
    public void doOpcode(CpuObject parameterObj) {
	     if(!isDoOpcode())return;
    	OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
    	Operand operand1=operandIo.getFirstOperandType(parameterObj);
    	if(operand1 instanceof Immediate){
    		pushImm(operand1);
    	}
    }
    
    private void pushImm(Operand operand1){
    	Immediate immValue=(Immediate)operand1;
    	IOperatorNum operatorValue=immValue.getImmediate();
    	DWord dwordValue=new DWord(operatorValue.toHexString());
    	CpuStack.instance.push(dwordValue);
     }
  }
