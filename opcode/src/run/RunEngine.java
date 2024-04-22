package run;

import java.util.List;

import memory.MemoryEngine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tool.ClearDebugData;
import util.GetACT;
import util.HexCodeUtil;
import cpu.CpuEngine;
import cpu.status.CpuStatus;

public class RunEngine {
	public static Logger logger = LogManager.getLogger(RunEngine.class);
	//private String endVirtualAddress="004047f2";
    public void run(String path){
    	     GetACT.getApplicationContext();
        	 MemoryEngine memoryE=(MemoryEngine)GetACT.getBean("memoryEngine");
        	 memoryE.loadFromPeFile(path);
    		 String startEip=memoryE.getStartEip();
    		 CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
    		// startEip="00401000";
    		 startEip="00402000";
    		 cpuStatus.setEip(startEip);
    		 cpuStatus.setMemoryEngine(memoryE);
    		 CpuEngine cpuEngine=new CpuEngine();
    		 for (int i = 0; i <32000; i++) { //18//差不多有3万2000行代码;32000
    			 if(cpuEngine.isFindError()){
    				 logger.info("find error!");
    				 return;
    			 }
    			 cpuEngine.clearParameterList();
    		     String eip=cpuStatus.getEip();
    			 List<Integer> codeLst=memoryE.getCodeOfRva(eip);
    			 String hexCode=bytes2HexStringAddComma(codeLst);
    			 logger.info("\n");
    		     logger.info("i:"+i+"--eip:"+cpuStatus.getEip()+"---code:"+hexCode);
    			 cpuEngine.decode(codeLst,cpuEngine);
    			// if(cpuStatus.getEip().equals(endVirtualAddress))break;
    		}
    }
    
	private  String bytes2HexStringAddComma(List<Integer> codeLst){
		byte[] b=new byte[codeLst.size()];
		for (int i = 0; i < codeLst.size(); i++) {
			Integer codeInt=codeLst.get(i);
			Byte btemp=codeInt.byteValue();
			b[i]=btemp;
		}
		String hexCode=HexCodeUtil.bytes2HexString(b);
		StringBuffer hexCodeSuf=new StringBuffer();
		for(int i=0;i<hexCode.length();i+=2){
			String hexCodeSubStr=hexCode.substring(i,i+2);
			if(i==0)hexCodeSuf.append(hexCodeSubStr);
			else hexCodeSuf.append(",").append(hexCodeSubStr);
		}
		return hexCodeSuf.toString();
	}
    
    public static void main(String[] args) {
        ClearDebugData c=new ClearDebugData();
        c.clearDb();
        String path="D:\\java\\opcode\\test.exe";
	    //  String path="F:\\redmoongw\\RedmoonRedMoon.exe";
       //  String path="F:\\redmoongw\\Redmoon\\Redmoon.exe";
		// String path="G:\\cpu\\是男人就撑过20秒.exe";
		// String path="G:\\redmoon\\redmoon38\\RMC.exe";
		//String path="G:\\redmoon38\\RMC.exe";
		//String path="G:\\pe4.8.5\\PRO.exe";
		RunEngine e=new RunEngine(); 
		e.run(path);
	   }
 }
