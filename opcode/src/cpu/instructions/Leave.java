package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Leave extends CpuObject {
	   public void leave_handler(List<Integer> frameLst,CpuObject parameterObj){
	       logger.info(" Leave ");
	    }
	
}
