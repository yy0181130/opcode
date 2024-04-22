package cpu.instructParent;

import java.util.List;

import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

public abstract class AddParent extends CpuObject {
	@Override
	public void decode(List<Integer> frameLst, CpuObject parameterObj) {
	    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
	    operandsParser.decode("add", frameLst, parameterObj);
	}
	
}
