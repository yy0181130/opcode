package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Int3 extends CpuObject {
	   public void int3_handler(List<Integer> frameLst,CpuObject parameterObj){
	       logger.info(" int 3 ");
	    }
	
}
