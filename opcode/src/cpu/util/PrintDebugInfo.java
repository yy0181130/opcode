package cpu.util;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import structs.DWord;
import util.GetACT;

import cpu.register.Register;
import cpu.stack.CpuStack;
import cpu.status.CpuStatus;

public class PrintDebugInfo {
	public static Logger logger = LogManager.getLogger(PrintDebugInfo.class);
	
   public static void print(){
	   printRegister();
	   printStack();
   }
   
   
   private static void printRegister(){
	   CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
	   StringBuffer info=new StringBuffer();
	   info.append("eax:").append(Register.eax.getValue().toHexString()).append("  ");
	   info.append("ecx:").append(Register.ecx.getValue().toHexString()).append("  ");
	   info.append("edx:").append(Register.edx.getValue().toHexString()).append("  ");
	   info.append("ebx:").append(Register.ebx.getValue().toHexString()).append("  ");
	   info.append("esp:").append(Register.esp.getValue().toHexString()).append("  ");
	   info.append("ebp:").append(Register.ebp.getValue().toHexString()).append("  ");
	   info.append("esi:").append(Register.esi.getValue().toHexString()).append("  ");
	   info.append("edi:").append(Register.edi.getValue().toHexString()).append("  ");
	   info.append("eip:").append(cpuStatus.getEip()).append("  ");
	   logger.info(info);
   }
   
   
   private static void printStack(){
	   LinkedList<DWord> cpuStack= CpuStack.instance.getCpuStack();
	   StringBuffer info=new StringBuffer();
//	   for (int i = 1; i < 3; i++) {
//		   int index=cpuStack.size()-i;
//		   if(index<0)return;
//		   DWord value=cpuStack.get(index);
//		   info.append(value.toHexString()).append(";").append(" ");
//		}
	   
	   for (DWord dWord : cpuStack) {
		   info.append(dWord.toHexString()).append(";").append(" ");
	   }
	   logger.info(info);
	 }
   }
