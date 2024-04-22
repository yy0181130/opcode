package cpu.aid;

import org.springframework.stereotype.Service;

import util.GetACT;
import cpu.parent.CpuObject;

/**
 * 
 * @Description:TODO
 * @author youy
 * @time:2014-3-19
 */

@Service
public class ShowAssembler extends CpuObject{
	
	public void show(CpuObject parameterObj){
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
		StringBuffer code=new StringBuffer();
		code.append(operandIo.getOpcode(parameterObj)).append(" ");//打印出指令
		code.append(operandIo.getFirstOperand(parameterObj));
		String secondOperand=operandIo.getSecondOperand(parameterObj);
		if(!secondOperand.equals(""))code.append(",");
		code.append(operandIo.getSecondOperand(parameterObj));
		logger.info(code);
	}

}
