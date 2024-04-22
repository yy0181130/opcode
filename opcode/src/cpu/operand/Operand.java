package cpu.operand;

import cpu.bean.InstructParserBean;
import cpu.operand.bean.OperandContextBean;
import cpu.operand.choose.OperandType;
import cpu.parent.CpuObject;


public abstract class Operand {
	    public abstract void setOperandContextBean(OperandContextBean contextBean);
	    
	    private OperandType operandType;//操作数类型
	    private int operandSize=32;//操作数长度;
	    private CpuObject parameterObj;
	    public Operand(CpuObject parameterObj) {
	    	InstructParserBean prefixBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
	    	operandSize=prefixBean.getEffectiveOperandSize().getEffectiveSize().getOperandLength();
	    	this.parameterObj=parameterObj;
		}
	    
		public CpuObject getParameterObj() {
			return parameterObj;
		}


		public OperandType getOperandType() {
			return operandType;
		}
		public void setOperandType(OperandType operandType) {
			this.operandType = operandType;
		}
		public int getOperandSize() {
			return operandSize;
		}
		public void setOperandSize(int operandSize) {
			this.operandSize = operandSize;
		}
		
		public Integer toDecValue(){
			return null;
		};
		
}
