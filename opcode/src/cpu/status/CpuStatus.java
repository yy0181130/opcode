package cpu.status;

import memory.MemoryEngine;
import memory.util.MemoryAddressUtil;

import org.springframework.stereotype.Component;

import structs.DWord;
import cpu.bean.SwithTableBean;



@Component
public class CpuStatus {
	private CpuStatusBean cpu;
	private MemoryEngine memoryEngine;
	private  SwithTableBean swithTableBean=new SwithTableBean();

    public SwithTableBean getSwithTableBean() {
		return swithTableBean;
	}
	public void setSwithTableBean(SwithTableBean swithTableBean) {
		this.swithTableBean = swithTableBean;
	}
	public CpuStatus() {
		cpu=new CpuStatusBean();
 	}
	public void setEip(String eip){
		cpu.getEip().refreshValue(eip);
	}
	public String getEip(){
		return cpu.getEip().toHexString();
	}
	public DWord GetEipDWord(){
		return cpu.getEip();
	}
	
	 public MemoryEngine getMemoryEngine() {
		return memoryEngine;
	}
	public void setMemoryEngine(MemoryEngine memoryEngine) {
		this.memoryEngine = memoryEngine;
	}
	public void setEipOfSize(int codeSize){
    	String address=MemoryAddressUtil.addressAdd(cpu.getEip().toHexString(), codeSize);
    	cpu.getEip().refreshValue(address);
    }


}
