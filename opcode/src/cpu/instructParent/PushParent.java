package cpu.instructParent;

import java.util.List;

import structs.DWord;
import util.GetACT;
import util.HexCodeUtil;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;
import cpu.register.Register;
import cpu.stack.CpuStack;

public abstract class PushParent extends CpuObject{
	  final String thisMedName="push_"; 
	  
		@Override
		public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("push", frameLst, parameterObj);
		}

		
		public void push_eAX(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push eax");
	    } 
		
		public void push_eCX(List<Integer> frameLst, CpuObject cpuObj){
		    logger.info("push ecx");
		 } 
	    public void push_eDX(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push edx");
	    	
	    } 
		
	    public void push_eBX(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push ebx");
	    	DWord value=Register.ebx.getDword();
	    	CpuStack.instance.push(value);
	    } 
	    
	    public void push_eSP(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push esp");
	    } 
	    public void push_eBP(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push ebp");
	    } 
	    public void push_eSI(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push esi");
	    } 
	    public void push_eDI(List<Integer> frameLst, CpuObject cpuObj){
	    	logger.info("push edi");
	    } 
	    
	    public void push_Ib(List<Integer> frameLst,CpuObject parameterObj){
	    	Integer operant=frameLst.get(1);
	    	addCurrenIndex(parameterObj);
	    	Byte operantByte=operant.byteValue();
	        String hexValue= HexCodeUtil.byteValueToHexStr(operantByte);
	        logger.info("push "+hexValue);
	    }
	    
	    public void push_Iz(List<Integer> frameLst,CpuObject parameterObj){
	    	DWord operand=new DWord(frameLst,1);
	    	addCurrenIndex(parameterObj,4);
	        String operandStr= operand.toHexString();
	        logger.info("push "+operandStr);
	    }
}
