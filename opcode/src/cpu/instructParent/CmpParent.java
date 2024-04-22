package cpu.instructParent;

import java.util.List;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

public abstract class CmpParent extends CpuObject {
	
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("cmp", frameLst, parameterObj);
	   }
	
    public void cmp_Gv_Ev(List<Integer> frameLst,CpuObject parameterObj){
        OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	    operandsParser.decode("cmp", frameLst, parameterObj);
     }
	 
}
