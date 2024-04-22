package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.parent.CpuObject;

@Component
public class Nop extends CpuObject{
    public void nop_handler(List<Integer> frameLst,CpuObject parameterObj){
       logger.info(" NOP ");
    }
	
}
