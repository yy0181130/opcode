package cpu.instructions;

import java.util.ArrayList;
import java.util.List;

import memory.util.MemoryAddressUtil;

import org.springframework.stereotype.Component;

import structs.DWord;
import cpu.parent.CpuObject;
import cpu.util.CpuUtil;

@Component
public class Jmp extends CpuObject{
    public void jmp_Jz(List<Integer> frameLst,CpuObject parameterObj){
       DWord machineAddress=new DWord((ArrayList<Integer>)frameLst,1);
       addCurrenIndex(parameterObj,4);
       String rVaAddress= CpuUtil.getDWordRvaAddressByAbsoluteAddress(machineAddress);
       logger.info("jmp "+rVaAddress);
    }
	
    public void jmp_Jb(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jumDistance=frameLst.get(1);
    	addCurrenIndex(parameterObj,1);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jumDistance+2);
        logger.info("jmp short "+jumpAddress);
     } 
    
}
