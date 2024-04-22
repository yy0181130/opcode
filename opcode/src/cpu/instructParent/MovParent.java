package cpu.instructParent;

import java.util.List;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

public abstract class MovParent extends CpuObject {
	
  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
	    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	    operandsParser.decode("mov", frameLst, parameterObj);
   }
     public void mov_Gv_Ev(List<Integer> frameLst,CpuObject parameterObj){
	    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	    operandsParser.decode("mov", frameLst, parameterObj);
      } 
	 
	 public void mov_Ev_Gv(List<Integer> frameLst,CpuObject parameterObj){
		 OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		 operandsParser.decode("mov", frameLst, parameterObj);
	  }  
	 
	public void mov_rAX_Ov(List<Integer> frameLst,CpuObject parameterObj){
		 OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		 operandsParser.decode("mov", frameLst, parameterObj);
	}  
	 
}
