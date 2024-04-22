package cpu.stack;

import java.util.LinkedList;

import cpu.register.Register;
import cpu.util.HexCalculationUtil;
import structs.DWord;



public enum CpuStack {
	instance;
   private LinkedList<DWord> cpuStack;
   private CpuStack() {
	   cpuStack=new LinkedList<DWord>();
	 }
   public void push(DWord value){
	  cpuStack.push(value);
	  String s=HexCalculationUtil.addressStract( Register.esp.getValue().toHexString(), 4);
	  DWord value2=new DWord(s);
	  Register.esp.setValue(value2);
	}
   
   
   public LinkedList<DWord> getCpuStack(){
	   return cpuStack;
   }
   
   public void init(DWord value){
	  cpuStack.push(value);
	}
   
}
