package cpu.instructParent;

import java.util.List;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

public abstract class LeaParent extends CpuObject {
	
    public void lea_Gv_M(List<Integer> frameLst,CpuObject parameterObj){
        OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	    operandsParser.decode("lea", frameLst, parameterObj);
     }
	 
}
