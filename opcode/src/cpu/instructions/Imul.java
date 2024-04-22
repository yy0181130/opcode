package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import structs.DWord;
import util.GetACT;
import cpu.aid.ModRM;
import cpu.aid.OperandsParser;
import cpu.bean.ModRMBean;
import cpu.parent.CpuObject;
import cpu.register.Register;

@Component
public class Imul extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("imul", frameLst, parameterObj);
	   }
	  
	  public void decode_GvEvIz(List<Integer> frameLst, CpuObject parameterObj) {
          OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
          operandsParser.decode("imul", frameLst, parameterObj);
          //Gv
          ModRM modRm = (ModRM) GetACT.getBean("modRM");
          ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
          if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
          String r32 = modRm.getR32(modRBean);
          //Ev
          DWord secondNum=new DWord(frameLst,2);
          DWord thirdNum=new DWord(frameLst,6);
          StringBuffer code=new StringBuffer();
           code.append("imul").append(" ");
           code.append(r32).append(", ");
           code.append("dword prt ds:[").append(secondNum.toHexString()).append("], ");
           code.append(thirdNum.toHexString()); 
           logger.info(code.toString());
           setInstructLength(parameterObj,10);
          
     }
     
	  
}
