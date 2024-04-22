package cpu.instructions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import structs.DWord;
import cpu.parent.CpuObject;
import cpu.util.CpuUtil;

@Component
public class Call extends CpuObject{
    public void call_Jz(List<Integer> frameLst,CpuObject parameterObj){
       DWord machineAddress=new DWord((ArrayList<Integer>)frameLst,1);
       String rVaAddress= CpuUtil.getDWordRvaAddressByAbsoluteAddress(machineAddress);
       logger.info("call "+rVaAddress);
       setInstructLength(parameterObj, 5);
//       String code="call "+rVaAddress;
//       SaveMovToDb saveMov=(SaveMovToDb)GetACT.getBean("saveMovToDb");
//       saveMov.saveCallInstruction(parameterObj,code);
       
    }
	
}
