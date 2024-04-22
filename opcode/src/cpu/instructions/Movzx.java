package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import structs.DWord;
import structs.UByte;
import util.GetACT;
import cpu.aid.CreateOpcodeStr;
import cpu.aid.OperandsParser;
import cpu.instructParent.MovParent;
import cpu.parent.CpuObject;

@Component
public class Movzx extends MovParent{
	
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("movzx", frameLst, parameterObj);
	   }
	  
	  public void movzx_eax_ax(List<Integer> frameLst,CpuObject parameterObj){
		     logger.info("movzx eax,ax");
	    }  
	  
	  public void movzx_eax_byteptr_$eax_disp8(List<Integer> frameLst,CpuObject parameterObj){
	    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
			logger.info(createOpcodeStr.getOpcode("movzx_eax_byteptr_$eax_disp8", frameLst, parameterObj));
			setInstructLength(parameterObj,4);
	    }  
	  
	 
	  
	  public void movzx_edx_wordprt_$esp_disp8(List<Integer> frameLst,CpuObject parameterObj){
		    UByte opand2=new UByte(frameLst, 4);
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" edx,word ptr ss:[esp+");
			code.append(opand2.toHexString()).append("]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,5);
	    }  
	  
	  public void movzx_edx_wordprt_$edx2_disp32(List<Integer> frameLst,CpuObject parameterObj){
		    DWord disp32=new DWord(frameLst, 4);
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" edx,word ptr ss:[edx*2+");
			code.append(disp32.toHexString()).append("]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,8);
	    }  
	  
	  
	  public void movzx_ebx_wordprt_$ebx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" ebx,word ptr ds:[ebx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,3);
	    } 
	  
	  public void movzx_ebx_wordprt_$edi(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" ebx,word ptr ds:[edi]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,3);
	    } 
	  
	  
	  public void movzx_esi_wordprt_$ebx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" esi,word ptr ds:[ebx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,3);
	    } 
	  
	  public void movzx_esi_byteptr_$ecx_disp8(List<Integer> frameLst,CpuObject parameterObj){
		    UByte disp8=new UByte(frameLst, 3);
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" esi,byte ptr ds:[ecx+");
			code.append(disp8.toHexString()).append("]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,4);
	    }  
	  
	  public void movzx_esi_wordprt_$edx(List<Integer> frameLst,CpuObject parameterObj){
		    StringBuffer code=new StringBuffer();
			code.append("movzx").append(" esi,word ptr ds:[edx]");
		    logger.info(code.toString());
			setInstructLength(parameterObj,3);
	    } 
}
