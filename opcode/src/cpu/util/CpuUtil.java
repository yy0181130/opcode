package cpu.util;

import memory.util.MemoryAddressUtil;
import structs.DWord;
import util.GetACT;
import cpu.status.CpuStatus;

public class CpuUtil {
    public static String getDWordRvaAddressByAbsoluteAddress(DWord address){
         CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
         String eip= cpuStatus.getEip();
    	 String newAddress1= MemoryAddressUtil.addressAdd(address.toHexString(), eip);
         String newAddress2= MemoryAddressUtil.addressAdd(newAddress1, 5);
         return newAddress2;
    }
    
    
    public static String getEip(){
    	  CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
          String eip= cpuStatus.getEip();
          return eip;
    }
    
    
    public static void addUnderLineOfMed(StringBuffer medName){
    	  if(medName.length()==0)return ;
    	  String medNameStr=medName.toString();
    	  String medLast=medNameStr.substring(medNameStr.length()-1);
    	  if(!medLast.equals("_")){
    		  medName.append("_");
    	  }
    }
}
