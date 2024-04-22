package cpu.operand.bean;

import cpu.operand.Operand;
import cpu.parent.CpuBeanObj;

public class OperandBean extends CpuBeanObj{
	private Operand firstOperand;//第一操作数;
    private Operand secondOperand;//第二操作数;
    private Operand currentOperand;//当前使用的操作数
    private int currentOperandIndex=1;//当前使用的操作数计数;
    private String opcode;//指令
    
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public int getCurrentOperandIndex() {
		return currentOperandIndex;
	}
	public void setCurrentOperandIndex(int currentOperandIndex) {
		this.currentOperandIndex = currentOperandIndex;
	}
	public Operand getCurrentOperand() {
		return currentOperand;
	}
	public void setCurrentOperand(Operand currentOperand) {
		this.currentOperand = currentOperand;
	}
	public Operand getFirstOperand() {
		return firstOperand;
	}
	public void setFirstOperand(Operand firstOperand) {
		this.firstOperand = firstOperand;
	}
	public Operand getSecondOperand() {
		return secondOperand;
	}
	public void setSecondOperand(Operand secondOperand) {
		this.secondOperand = secondOperand;
	}
	
	public void addCurrentIndex(){
		currentOperandIndex++;
	}
    
    
    
}
