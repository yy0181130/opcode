package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.instructParent.CmpParent;
import cpu.parent.CpuObject;

@Component
public class Dec extends CmpParent{
    public void dec_eAX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec eax");
     }
    public void dec_eCX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec ecx");
     }
    public void dec_eDX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec edx");
     }
    public void dec_eBX(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec ebx");
     }
    public void dec_eSP(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec esp");
     }
    public void dec_eBP(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec ebp");
     }
    public void dec_eSI(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec esi");
     }
    public void dec_eDI(List<Integer> frameLst,CpuObject parameterObj){
        logger.info("dec edi");
     }
  }
