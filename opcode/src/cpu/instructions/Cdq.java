package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Cdq extends CpuObject{
    public void cdq_handler(List<Integer> frameLst,CpuObject parameterObj){
    	  logger.info(" cdq ");
    }
	
}
