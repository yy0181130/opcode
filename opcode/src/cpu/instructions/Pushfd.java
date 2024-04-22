package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Pushfd extends CpuObject{
    public void pushfd_handler(List<Integer> frameLst,CpuObject parameterObj){
    	  logger.info(" pushfd ");
    }
	
}
