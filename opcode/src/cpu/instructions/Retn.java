package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import structs.Word;
import util.GetACT;
import cpu.aid.OperandsParser;
import cpu.parent.CpuObject;

@Component
public class Retn extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("retn", frameLst, parameterObj);
	   }
	
	
    public void retn_handler(List<Integer> frameLst,CpuObject parameterObj){
       logger.info(" retn ");
    }
    
    public void retn_Iw(List<Integer> frameLst,CpuObject parameterObj){
    	Word firstOpand=new Word(frameLst,1);
        logger.info("retn "+firstOpand.toHexString());
     }
	
}
