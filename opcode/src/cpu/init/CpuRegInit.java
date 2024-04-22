package cpu.init;

import structs.DWord;
import cpu.register.Register;
import cpu.stack.CpuStack;

public class CpuRegInit {
	public void initValue(){
		initValuesC();
	}
	
   private void initValuesA(){
	   Register.eax.setValue(new DWord("7736EE0A"));
	   Register.ecx.setValue(new DWord("00000000"));
	   Register.edx.setValue(new DWord("00402020"));
	   Register.ebx.setValue(new DWord("7FFDE000"));
	   Register.esp.setValue(new DWord("0006FF8C"));
	   Register.ebp.setValue(new DWord("0006FF94"));
	   Register.esi.setValue(new DWord("00000000"));
	   Register.edi.setValue(new DWord("00000000"));
	   
	   CpuStack.instance.init(new DWord("7FFD4000"));
	 }
   
   private void initValuesB(){
	   Register.eax.setValue(new DWord("00000000"));
	   Register.ecx.setValue(new DWord("0006FFB0"));
	   Register.edx.setValue(new DWord("7C92E514"));
	   Register.ebx.setValue(new DWord("7FFDE000"));
	   Register.esp.setValue(new DWord("0006FFC4"));
	   Register.ebp.setValue(new DWord("0006FFF0"));
	   Register.esi.setValue(new DWord("00000000"));
	   Register.edi.setValue(new DWord("7C930228"));
	   
	  
	   CpuStack.instance.init(new DWord("7C930228"));
	   CpuStack.instance.init(new DWord("7C816037"));
	  }
   
   private void initValuesC(){
	   Register.eax.setValue(new DWord("00000000"));
	   Register.ecx.setValue(new DWord("0012FFB0"));
	   Register.edx.setValue(new DWord("7C92E514"));
	   Register.ebx.setValue(new DWord("7FFDF000"));
	   Register.esp.setValue(new DWord("0012FFC4"));
	   Register.ebp.setValue(new DWord("0012FFF0"));
	   Register.esi.setValue(new DWord("FFFFFFFF"));
	   Register.edi.setValue(new DWord("7C930228"));
	   
	  
	   CpuStack.instance.init(new DWord("7C930228"));
	   CpuStack.instance.init(new DWord("7C816037"));
	  }
   
}
