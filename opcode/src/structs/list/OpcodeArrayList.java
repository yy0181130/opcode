package structs.list;

import java.util.ArrayList;

import cpu.CpuEngine;

public class OpcodeArrayList<T> extends ArrayList<T>{
	private CpuEngine cpuEngine;
	private static final long serialVersionUID = 1L;
    private boolean isStartAddIndex;
	public void setCpuEngine(CpuEngine cpuEngine) {
		this.cpuEngine = cpuEngine;
	}
	
	public void setStartAddIndex(boolean isStartAddIndex) {
		this.isStartAddIndex = isStartAddIndex;
	}


	public OpcodeArrayList() {
	}
	
	@Override
	public T get(int index) {
		if(isStartAddIndex==true)
		   cpuEngine.addCurrenIndex(cpuEngine);
		return super.get(index);
	}
  
}
