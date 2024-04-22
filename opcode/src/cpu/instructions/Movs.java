package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.bean.PrefixBean;
import cpu.instructParent.MovParent;
import cpu.parent.CpuObject;

@Component
public class Movs extends MovParent{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("movs", frameLst, parameterObj);
	   }
	  
      public void movs_a4(List<Integer> frameLst,CpuObject parameterObj){
          PrefixBean prefixBean=(PrefixBean)parameterObj.getParameter(PrefixBean.class.getSimpleName());
          StringBuffer code=new StringBuffer();
          if(prefixBean.isHaveRep()){
            code.append("rep ");
          }
          code.append("movs byte ptr es:[edi],byte ptr ds:[esi]");
          logger.info(code.toString());
          setInstructLength(parameterObj,1);
      }  
	  
	  public void movs_a5(List<Integer> frameLst,CpuObject parameterObj){
			PrefixBean prefixBean=(PrefixBean)parameterObj.getParameter(PrefixBean.class.getSimpleName());
			StringBuffer code=new StringBuffer();
			if(prefixBean.isHaveRep()){
		      code.append("rep ");
		    }
			code.append("movs dword ptr es:[edi],dword ptr ds:[esi]");
		    logger.info(code.toString());
		 	setInstructLength(parameterObj,1);
	    }  
	  

	  
}
