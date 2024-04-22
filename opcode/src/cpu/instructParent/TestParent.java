package cpu.instructParent;

import java.util.List;

import util.GetACT;
import cpu.aid.ModRM;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

public abstract class TestParent extends CpuObject {
	@Override
	public void decode(List<Integer> frameLst, CpuObject parameterObj) {
	      OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	      operandsParser.decode("test", frameLst, parameterObj);
	}
	public void test_Ev_Gv(List<Integer> frameLst, CpuObject parameterObj) {
        ModRM modRm = (ModRM) GetACT.getBean("modRM");
		modRm.setModRm(frameLst, parameterObj);
		OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode("test", frameLst, parameterObj);
     }
	


}
