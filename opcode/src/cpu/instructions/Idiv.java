package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

@Component
public class Idiv extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("idiv", frameLst, parameterObj);
	   }
	  
   }
