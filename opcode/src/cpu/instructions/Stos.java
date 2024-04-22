package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.bean.InstructParserBean;
import cpu.parent.CpuObject;

@Component
public class Stos extends CpuObject{
    
    public void decode(List<Integer> frameLst, CpuObject parameterObj) {
      InstructParserBean prefixBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
      boolean rep= prefixBean.isRepFlag();
      if(rep){
          logger.info(" rep stos dword ptr es:[edi]");
      }else{
          logger.warn("----error!!-------");
      }
    }
    
    public void decode_byte(List<Integer> frameLst, CpuObject parameterObj) {
        InstructParserBean prefixBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
        boolean rep= prefixBean.isRepFlag();
        if(rep){
            logger.info(" rep stos byte ptr es:[edi]");
        }else{
            logger.warn("----error!!-------");
        }
      }

}
