package cpu.aid;

import org.springframework.stereotype.Service;

import cpu.operand.Operand;
import cpu.operand.bean.OperandBean;
import cpu.operand.bean.OperandContextBean;
import cpu.operand.choose.OperandType;
import cpu.parent.CpuObject;
import cpu.parent.OperandCommonIo;
/**
 * 操作数相关操作
 */
@Service
public class OperandIo extends OperandCommonIo {
    
    /**
     * 设置操作数的类型,并且根据传进来的类型选择不同的处理类处理
     * @param parameterObj
     * @param operandType
     */
	public void setOperandType(CpuObject parameterObj,OperandType operandType) {
	   OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
	   Operand operand=operandType.getOperandType(parameterObj);
	   operand.setOperandType(operandType);
	   if(operandBean.getCurrentOperandIndex()==1) operandBean.setFirstOperand(operand);
	   else operandBean.setSecondOperand(operand);
	   operandBean.setCurrentOperand(operand);
	   operandBean.addCurrentIndex();
	}
    
	/**
	 * 将统一的操作数信息设置到具体的处理类中
	 * @param parameterObj
	 * @param contextBean
	 */
	public void setOperanadContext(CpuObject parameterObj,OperandContextBean contextBean) {
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		Operand currentOperand=operandBean.getCurrentOperand();
		currentOperand.setOperandContextBean(contextBean);
	}
	
	public String getOpcode(CpuObject parameterObj){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		return operandBean.getOpcode();
	}
	
	public void setOperandSize(CpuObject parameterObj,int operandSize){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		operandBean.getCurrentOperand().setOperandSize(operandSize);
	}
	
	public String getFirstOperand(CpuObject parameterObj){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		Operand firstOperand=operandBean.getFirstOperand();
		if(firstOperand==null)return "";
		return firstOperand.toString();
	}
	
	public Operand getFirstOperandType(CpuObject parameterObj){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		Operand firstOperand=operandBean.getFirstOperand();
		if(firstOperand==null)return null;
		return firstOperand;
	}
	
	
	public String getSecondOperand(CpuObject parameterObj){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		Operand secondOperand=operandBean.getSecondOperand();
		if(secondOperand==null)return "";
		return secondOperand.toString();
	}
	
	public Operand getSecondOperandType(CpuObject parameterObj){
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		Operand secondOperand=operandBean.getSecondOperand();
		if(secondOperand==null)return null;
		return secondOperand;
	}
 }
