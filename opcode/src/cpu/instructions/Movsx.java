package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import structs.UByte;
import util.GetACT;
import cpu.aid.CreateOpcodeStr;
import cpu.aid.OperandsParser;
import cpu.instructParent.MovParent;
import cpu.parent.CpuObject;

@Component
public class Movsx extends MovParent{
	
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("movsx", frameLst, parameterObj);
	   }
	  
	  public void movsx_eax_byteptr_$eax_disp8(List<Integer> frameLst,CpuObject parameterObj){
	    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
	        logger.info(createOpcodeStr.getOpcode("movsx_eax_byteptr_$eax_disp8", frameLst, parameterObj));
	    	setInstructLength(parameterObj,4);
	 	 }
	  
	  public void movsx_eax_ax(List<Integer> frameLst,CpuObject parameterObj){
		     logger.info("movsx eax,ax");
	    }  
	  
	  
	  public void movsx_eax_byteptr_$eaxecx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();  
		    code.append("movsx eax,byte ptr ds:[eax+ecx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,4);
	    }
	  
	  
	  public void movsx_ecx_byteptr_$edx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();  
		    code.append("movsx ecx,byte ptr ds:[edx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,3);
	    }
	  
	  public void movsx_ecx_byteptr_$eax_disp8(List<Integer> frameLst,CpuObject parameterObj){
	    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
	        logger.info(createOpcodeStr.getOpcode("movsx_ecx_byteptr_$eax_disp8", frameLst, parameterObj));
	    	setInstructLength(parameterObj,4);
	 	 }
	  
	  public void movsx_ecx_byteptr_$edx_disp8(List<Integer> frameLst,CpuObject parameterObj){
	    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
	        logger.info(createOpcodeStr.getOpcode("movsx_ecx_byteptr_$edx_disp8", frameLst, parameterObj));
	    	setInstructLength(parameterObj,4);
	 	 } 
	  
	  public void movsx_ecx_byteptr_$eaxedx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();  
		    code.append("movsx ecx,byte ptr ds:[eax+edx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,4);
	    }
	  
	  public void movsx_edx_wordprt_$esp_disp8(List<Integer> frameLst,CpuObject parameterObj){
		    UByte opand2=new UByte(frameLst, 4);
		    StringBuffer code=new StringBuffer();
			code.append("movsx").append(" edx,word ptr ss:[esp+");
			code.append(opand2.toHexString()).append("]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,5);
	    }  
	  
	  public void movsx_edx_byteptr_$eax_disp8(List<Integer> frameLst,CpuObject parameterObj){
	    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
	        logger.info(createOpcodeStr.getOpcode("movsx_edx_byteptr_$eax_disp8", frameLst, parameterObj));
	    	setInstructLength(parameterObj,4);
	 	 } 
	  
	  
	  
}
