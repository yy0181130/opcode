package cpu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import structs.DWord;
import structs.UByte;
import util.ArrayUtil;
import util.ByteUtil;
import util.GetACT;
import cpu.aid.FirstByteParser;
import cpu.aid.PrefixParser;
import cpu.bean.InstructParserBean;
import cpu.bean.SwithTableBean;
import cpu.init.CpuRegInit;
import cpu.operand.bean.OperandBean;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;
/**
 * 全部指令都是以byte转为int为主，即Byte&FF
 * @author youy
 * 切记,解析的过程并不转到独立的指令去解析
 *
 */
public class CpuEngine extends CpuObject{
	public static Logger logger = LogManager.getLogger(CpuEngine.class);
   public CpuEngine(){
	   //在整个指令运行当中,CPU状态被共享
	    CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
        setCpuStatus(cpuStatus);
        CpuRegInit init=new CpuRegInit();
        init.initValue();
   }
	
   @Override
	public void decode(List<Integer> frameLst, CpuObject cpuObj) {
	   if(!notZeroJudge(frameLst)){
		   logger.info("db 00");
		   setEipOfSize(1);
		   return;
	   }
	   boolean isStable=swithtablehandler(frameLst);
	   if(isStable)return;
	   InstructParserBean instructBean=new InstructParserBean();
	   instructBean.setFrameLst(frameLst);
	   cpuObj.addBean(instructBean);
	   OperandBean operandBean=new OperandBean();
	   cpuObj.addBean(operandBean);
	   
	   PrefixParser prefixParser=(PrefixParser)GetACT.getBean("prefixParser");
	   prefixParser.decode(frameLst, cpuObj);
	   
	   FirstByteParser firstByteParser=(FirstByteParser)GetACT.getBean("firstByteParser");
	   firstByteParser.decode(frameLst, cpuObj);
		 
		//PrintDebugInfo.print();
	   if(!instructBean.isManulSetEip()){
			setEipOfSize(instructBean.getCurrentIndex());
	   }
	}
   
   private boolean swithtablehandler(List<Integer> frameLst){
       CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
       String eip=cpuStatus.getEip();
       SwithTableBean stable=cpuStatus.getSwithTableBean();
       Set<String> stableSet=stable.getInstructStartPosationVector();
       boolean isContain=stableSet.contains(eip);
       if(isContain){
           DWord stableValue=new DWord(frameLst,0);
           logger.info("db "+stableValue.toHexString());
           stableSet.remove(eip);
           setEipOfSize(4);
           return true;
        }
       Set<String> branchIndexTable= stable.getBranchIndexTable();
       boolean isContainBranchTable=branchIndexTable.contains(eip);
       if(isContainBranchTable){
           UByte ubyte=new UByte(frameLst,0);
           logger.info("db "+ubyte.toHexString());
           stableSet.remove(eip);
           setEipOfSize(1);
           return true;
       }
       
       return false;
       
   }
   
   
   
   
   private boolean notZeroJudge(List<Integer> frameLst){
	   for (Integer code : frameLst) {
		if(code!=0){
			return true;
		}
	  }
	   return false;
   }
   
   
  	private void test_1(){
  		LinkedList<Integer> frameLst =new LinkedList<Integer>();
  		byte[] frameBytes = new byte[] {(byte)0xC7,0x44,0x24,0x08,0x01,0x00,0x00,0x00};
  		ArrayUtil.addByteToLinkedLst(frameBytes,frameLst);
  		decode(frameLst,this);
  	}
  	
  	private void test_2(){
  	    CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
  	    cpuStatus.setEip("00410dec");
  	    ArrayList<Integer> frameLst =new ArrayList<Integer>();
  		byte[] frameBytes = ByteUtil.fromStrWithBlank("C60401 01 ");
  		ArrayUtil.addByteToLinkedLst(frameBytes,frameLst);
  		decode(frameLst,this);
  	    int length= getCurrenIndex(this);
  	    System.out.println("length:"+length);
 	    System.out.println("error:"+isFindError());
  	}
  	
   public static void main(String[] args) {
	   GetACT.getApplicationContext();
	   CpuEngine c=new CpuEngine();
	   c.test_2();
	}
	
}
