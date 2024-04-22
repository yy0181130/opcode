package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import cpu.bean.InstructParserBean;
import cpu.parent.CpuObject;

@Component
public class Scas extends CpuObject{
    
    public void decode(List<Integer> frameLst, CpuObject parameterObj) {
      InstructParserBean prefixBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
      boolean repne= prefixBean.isRepneFlag();
      if(repne){
          logger.info(" repne scas byte ptr es:[edi]");
      }else{
          logger.warn("----error!!-------");
      }
    }

}
