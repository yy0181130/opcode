package cpu.instructions;

import java.util.List;

import memory.util.MemoryAddressUtil;

import org.springframework.stereotype.Component;

import structs.DWord;
import util.GetACT;
import cpu.aid.OperandIo;
import cpu.bean.SwithTableBean;
import cpu.instructParent.JmpParent;
import cpu.operand.Operand;
import cpu.operand.impl.Point;
import cpu.operand.impl.RegisterOperand;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

@Component
public class Jmpn extends JmpParent{
    public void jmpn_$ecx4_disp32(List<Integer> frameLst,CpuObject parameterObj){
    	DWord disp32=new DWord(frameLst,3);
    	StringBuffer code=new StringBuffer();
    	code.append("jmp dword ptr ds:[ecx*4+").append(disp32.toHexString()).append("]");
        logger.info(code.toString());
        setInstructLength(parameterObj, 7);
        addSwitchTable(parameterObj);
     } 
    
    public void jmpn_$eax4_disp32(List<Integer> frameLst,CpuObject parameterObj){
    	DWord disp32=new DWord(frameLst,3);
    	StringBuffer code=new StringBuffer();
    	code.append("jmp dword ptr ds:[eax*4+").append(disp32.toHexString()).append("]");//该地址就是一个分支表;
        logger.info(code.toString());
        setInstructLength(parameterObj, 7);
        addSwitchTable(parameterObj,disp32.toHexString());
     } 
    
    public void jmpn_$edx4_disp32(List<Integer> frameLst,CpuObject parameterObj){
    	DWord disp32=new DWord(frameLst,3);
    	StringBuffer code=new StringBuffer();
    	code.append("jmp dword ptr ds:[edx*4+").append(disp32.toHexString()).append("]");
        logger.info(code.toString());
        setInstructLength(parameterObj, 7);
        addSwitchTable(parameterObj);
     }
    
    public void jmpn_disp32(List<Integer> frameLst,CpuObject parameterObj){
    	DWord disp32=new DWord(frameLst,2);
    	StringBuffer code=new StringBuffer();
    	code.append("jmp dword ptr ds:[").append(disp32.toHexString()).append("]");
        logger.info(code.toString());
        setInstructLength(parameterObj, 6);
     }
    
    public void doOpcode(CpuObject parameterObj){
        judgeSwitchTable(parameterObj);
    }
    
    private void judgeSwitchTable(CpuObject parameterObj){
    	   OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
           Operand operand=operandIo.getFirstOperandType(parameterObj);
           if(!(operand instanceof Point))return;
           Point point=(Point)operand;
            if(!point.isJmpSwitableOfDbTable())return;
            if(point.getDisplacement().length()<5)return;
            
            CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
            SwithTableBean switchBean= cpuStatus.getSwithTableBean();
            DWord cmpIp=switchBean.getCmpIPPosation();
            if(cmpIp==null)  return ;
            DWord thisIp=cpuStatus.GetEipDWord();
            Integer subValue=  MemoryAddressUtil.addressSubtract(thisIp.toHexString(), cmpIp.toHexString());
            if(subValue>30)return;
            //if(!point.getSibIndex().equals(switchBean.getCmpRegName()))return;
            
            addSwitchTable(parameterObj,point.getDisplacement());
    }
    
    
    
    
 }
