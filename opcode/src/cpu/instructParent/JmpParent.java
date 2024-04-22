package cpu.instructParent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import memory.MemoryEngine;
import memory.util.MemoryAddressUtil;
import structs.DWord;
import structs.UByte;
import util.GetACT;
import cpu.bean.InstructParserBean;
import cpu.bean.SwithTableBean;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

public abstract class JmpParent extends CpuObject {
	
	  public void addSwitchTable(CpuObject parameterObj) {
		     boolean isNeedTable= isNeedTable(parameterObj);
		     if(!isNeedTable)return;
		    InstructParserBean instructBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
	        instructBean.setManulSetEip(true);
	        parameterObj.setEipOfSize(instructBean.getCurrentIndex());
	        CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
	        SwithTableBean switchBean= cpuStatus.getSwithTableBean();
	        if(switchBean.isDb()){
	        	addDbSwotcjTable(parameterObj);
	        }else{
	        	addDdSwotcjTable(parameterObj);
	        }
	     
	   }
	
	  public void addSwitchTable(CpuObject parameterObj,String baseAddr){
	      CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
	      SwithTableBean switchBean= cpuStatus.getSwithTableBean();
	      Integer switchTableLength=switchBean.getSwicthtableLength();
	      for (int i = 0; i <=switchTableLength; i++) {
	          String address=MemoryAddressUtil.addressAdd(baseAddr, i*4);
	          switchBean.addInstructStartPosationVector(address);
	      }
       }
	  
	  
	  private boolean isNeedTable(CpuObject parameterObj){
		   CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
		   SwithTableBean switchBean= cpuStatus.getSwithTableBean();
		   DWord cmpIp=switchBean.getCmpIPPosation();
		   if(cmpIp==null)  return false;
		   DWord thisIp=cpuStatus.GetEipDWord();
		   Integer subValue=  MemoryAddressUtil.addressSubtract(thisIp.toHexString(), cmpIp.toHexString());
		   if(subValue==9){
			   switchBean.setDb(false);
			   return true;
		   }else if(subValue==15){
			   switchBean.setDb(true);
			   return true;
		   }
		  return false;
	  }
	  
	  
	  private void addDdSwotcjTable(CpuObject parameterObj){
		  CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
	      SwithTableBean switchBean= cpuStatus.getSwithTableBean();
	      Integer switchTableLength=switchBean.getSwicthtableLength();
	        
	        MemoryEngine memoryE=cpuStatus.getMemoryEngine();
	        for (int i = 0; i <=switchTableLength; i++) {
	            List<Integer> codeLst=memoryE.getCodeOfRva(cpuStatus.getEip());
	        	DWord swichtable=new DWord(codeLst,0);
	        	logger.info("dd "+swichtable.toHexString());
	        	parameterObj.setEipOfSize(4);
			}
	  }
  
	  private void addDbSwotcjTable(CpuObject parameterObj){
		  CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
	      SwithTableBean switchBean= cpuStatus.getSwithTableBean();
	      Integer switchTableLength=switchBean.getSwicthtableLength();
	      Set<String> valueSet=new HashSet<String>();
	        MemoryEngine memoryE=cpuStatus.getMemoryEngine();
	        for (int i = 0; i <=switchTableLength; i++) {
	            List<Integer> codeLst=memoryE.getCodeOfRva(cpuStatus.getEip());
	        	UByte swichtable=new UByte(codeLst,0);
	        	String hexStr=swichtable.toHexString();
	        	valueSet.add(hexStr);
	        	logger.info("db "+swichtable.toHexString());
	        	parameterObj.setEipOfSize(1);
			}
	        addSecondJmpTable(parameterObj,valueSet);
	  }
	  
	  private void addSecondJmpTable(CpuObject parameterObj,Set<String> valueSet){
		  CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
		   MemoryEngine memoryE=cpuStatus.getMemoryEngine();
		  int length=valueSet.size();
		  for (int i = 0; i < length; i++) {
			  List<Integer> codeLst=memoryE.getCodeOfRva(cpuStatus.getEip());
			  DWord swichtable=new DWord(codeLst,0);
			  logger.info("dd "+swichtable.toHexString());
				parameterObj.setEipOfSize(4);
		 }
	  }
	
	  
	 
}
