package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

@Component
public class Sar extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("sar", frameLst, parameterObj);
	   }
	
	  public void sar_edx_1(List<Integer> frameLst,CpuObject parameterObj){
		  logger.info("sar edx,1");
		  setInstructLength(parameterObj,2);
	   }  


}
