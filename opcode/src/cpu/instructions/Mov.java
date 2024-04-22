package cpu.instructions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import memory.MemoryEngine;
import memory.util.MemoryAddressUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import structs.DWord;
import util.GetACT;
import cpu.aid.OperandIo;
import cpu.aid.SaveMovToDb;
import cpu.bean.SwithTableBean;
import cpu.instructParent.MovParent;
import cpu.operand.Operand;
import cpu.operand.impl.Point;
import cpu.operand.impl.RegisterOperand;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

@Component
public class Mov extends MovParent{
    public static Logger logger = LogManager.getLogger(Mov.class);
    
    @Override
    public void doOpcode(CpuObject parameterObj) {
        String code=showInMovLog(parameterObj);
         SaveMovToDb saveMov=(SaveMovToDb)GetACT.getBean("saveMovToDb");
         saveMov.chooseMovType(parameterObj,code);
        judgeSwithTable(parameterObj);
    }
    
    
    private String showInMovLog(CpuObject parameterObj){
        OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
        Operand  firstOperand=operandIo.getFirstOperandType(parameterObj);

        
        StringBuffer code=new StringBuffer();
        code.append(operandIo.getOpcode(parameterObj)).append(" ");//打印出指令
        code.append(operandIo.getFirstOperand(parameterObj));
        String secondOperand=operandIo.getSecondOperand(parameterObj);
        if(!secondOperand.equals(""))code.append(",");
        code.append(operandIo.getSecondOperand(parameterObj));
        
         if(firstOperand instanceof RegisterOperand){
             //logger.info("");//
         }else{
             logger.info(code);//
         }
        
        return code.toString();
    }
    
    private void judgeSwithTable(CpuObject parameterObj){
        OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
        Operand operand=operandIo.getFirstOperandType(parameterObj);
        if(!(operand instanceof RegisterOperand))return;
        RegisterOperand registerOperand=(RegisterOperand)operand;
         if(registerOperand.getRegSize()!=8)return;
        Operand operand2=operandIo.getSecondOperandType(parameterObj);
        if(!(operand2 instanceof Point))return;
         Point point=(Point)operand2;
         if(!point.issibBaseAndDisplacementNotNull())return;
         if(point.getDisplacement().length()<5)return;
        
        CpuStatus cpuStatus = (CpuStatus) GetACT.getApplicationContext().getBean("cpuStatus"); 
        SwithTableBean switchBean= cpuStatus.getSwithTableBean();
        DWord cmpIp=switchBean.getCmpIPPosation();
        if(cmpIp==null)  return ;
        DWord thisIp=cpuStatus.GetEipDWord();
        Integer subValue=  MemoryAddressUtil.addressSubtract(thisIp.toHexString(), cmpIp.toHexString());
        if(subValue>12)return;
        if(!point.getSibBase().equals(switchBean.getCmpRegName()))return;
        
        MemoryEngine memoryE=(MemoryEngine)GetACT.getBean("memoryEngine");
        
       for (int i = 0; i <= switchBean.getSwicthtableLength(); i++) {
            String address=MemoryAddressUtil.addressAdd(point.getDisplacement(), i);
            switchBean.addBranchIndexTableElement(address);
        }
        
        List<Integer> codeLst=memoryE.getCodeOfRva(point.getDisplacement(), switchBean.getSwicthtableLength()+1);
        Set<Integer> valueSet = new HashSet<Integer>();
        for (Integer value : codeLst) {
            valueSet.add(value);
        }
        switchBean.setSwicthtableLength(valueSet.size());
    } 
  }
