package cpu.operand.bean;

import structs.operator.IOperatorNum;
import cpu.register.Register;
import cpu.register.SegementRegister;
/**
 * 存放了这个操作数的信息
 */
public class OperandContextBean {
    private Register register;
    private IOperatorNum immediate;
    private Register sibBase;
    private Register sibIndex;
    private Integer  sibScale;
    private IOperatorNum displacement;
    private SegementRegister segementRegister;
    
	public SegementRegister getSegementRegister() {
		return segementRegister;
	}

	public void setSegementRegister(SegementRegister segementRegister) {
		this.segementRegister = segementRegister;
	}

	public Register getSibBase() {
		return sibBase;
	}

	public void setSibBase(Register sibBase) {
		this.sibBase = sibBase;
	}

	public Register getSibIndex() {
		return sibIndex;
	}

	public void setSibIndex(Register sibIndex) {
		this.sibIndex = sibIndex;
	}

	public Integer getSibScale() {
		return sibScale;
	}

	public void setSibScale(Integer sibScale) {
		this.sibScale = sibScale;
	}

	public IOperatorNum getDisplacement() {
		return displacement;
	}

	public void setDisplacement(IOperatorNum displacement) {
		this.displacement = displacement;
	}

	public IOperatorNum getImmediate() {
		return immediate;
	}

	public void setImmediate(IOperatorNum immediate) {
		this.immediate = immediate;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}


    
    
}
