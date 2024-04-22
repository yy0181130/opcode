package cpu.operand.choose;

import cpu.operand.Operand;
import cpu.operand.impl.Immediate;
import cpu.operand.impl.Point;
import cpu.operand.impl.RegisterOperand;
import cpu.operand.impl.SegementRegisterOperand;
import cpu.parent.CpuObject;


public enum OperandType {
    RegisterType{
       @Override
		public Operand getOperandType(CpuObject cpuObj) {
			return new RegisterOperand(cpuObj);
		}
    	
    },
    SegementRegisterType{
        @Override
		public Operand getOperandType(CpuObject cpuObj) {
			return new SegementRegisterOperand(cpuObj);
		}
    	
    },
    
    ImmediateType{
        @Override
		public Operand getOperandType(CpuObject cpuObj) {
			return new Immediate(cpuObj);
		}
    	
    },
    
    PointType{
        @Override
		public Operand getOperandType(CpuObject cpuObj) {
			return new Point(cpuObj);
		}
    };
    

   public  abstract Operand getOperandType(CpuObject cpuObj);


}
