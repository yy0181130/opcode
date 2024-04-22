package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.instructParent.CmpParent;
import cpu.parent.CpuObject;

@Component
public class Inc extends CmpParent{
    public void inc_eAX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc eax");
     }
    public void inc_eCX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc ecx");
     }
    public void inc_eDX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc edx");
     }
    public void inc_eBX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc ebx");
     }
    public void inc_eSP(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc esp");
     }
    public void inc_eBP(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc ebp");
     }
    public void inc_eSI(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc esi");
     }
    public void inc_eDI(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("inc edi");
     }
    
   
    
}
