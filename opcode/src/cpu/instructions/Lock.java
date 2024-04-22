package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

@Component
public class Lock extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("lock", frameLst, parameterObj);
	   }
	
 
	    public void lock_handler(List<Integer> frameLst,CpuObject parameterObj){
	        logger.info(" lock ");
	     }
}
