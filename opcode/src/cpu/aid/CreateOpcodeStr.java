package cpu.aid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import structs.DWord;
import structs.UByte;
import structs.Word;
import util.GetACT;
import cpu.instructParent.CreateOpcodeInfo;
import cpu.parent.CpuObject;

@Service
public class CreateOpcodeStr extends CreateOpcodeInfo{
    @SuppressWarnings("unused")
	private String opcode_reg_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(opcodeLst.get(2).toString());
    	return info.toString();
    }
    
    private String opcode_reg_$reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(disp8.toByteString()).append("]");
    	return info.toString();
    }
    
    private String opcode_reg_$reg_nop(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
    	return info.toString();
    }
    
    
    private String opcode_reg_$reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(disp8.toByteString()).append("]");
    	return info.toString();
    }
    
    private String opcode_reg_$reg_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	DWord disp32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(disp32.toHexString()).append("]");
    	return info.toString();
    }
    
    
    private String opcode_reg_$reg_reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(opcodeLst.get(3).toString()).append("+").append(disp8.toByteString()).append("]");
    	return info.toString();
    }
    
    
    private String opcode_reg_$regn_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String allName=opcodeLst.get(2).toString();
    	String regName=allName.substring(1,allName.length()-1);
    	String scaler=allName.substring(allName.length()-1,allName.length());
    	String sectionName=getSectionAddressName(regName);
    	int index=getCurrenIndex(parameterObj);
    	DWord disp32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(regName).append("*").append(scaler).append("+").append(disp32.toHexString()).append("]");
    	return info.toString();
    }
    
    
    private String opcode_reg_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
      	String regName=opcodeLst.get(1).toString();
    	String sectionName=getSectionAddressName(regName);
    	int index=getCurrenIndex(parameterObj);
        DWord disp32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(dwordptr).append(sectionName).append(":")
    	.append("[").append(disp32.toHexString()).append("]");
    	return info.toString();
    }
    
    private String opcode_reg_Iv(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord iv=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(iv.toHexString());
    	return info.toString();
    }
    
    private String opcode_reg_Iz(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord iz=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(iz.toHexString());
    	return info.toString();
    }
    
    
    
    
    private String opcode_reg_Ib(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        UByte ib=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(ib.toHexString());
    	return info.toString();
    }
    
    
    private String opcode_reg_byteptr_$reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(3).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1)).append(",");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        return info.toString();
    }
    
    private String opcode_$reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
        return info.toString();
    }
    
    private String opcode_$reg_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
    	info.append(",").append(opcodeLst.get(2).toString());
    	return info.toString();
    }
    
    private String opcode_$reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
    	info.append(",").append(disp8.toHexString());
    	return info.toString();
    }
    
    private String opcode_$reg_disp8_oneOpand(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName);
    	info.append("+").append(disp8.toHexString()).append("]");
    	return info.toString();
    }
    
    
    private String opcode_$reg_disp8_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte disp8=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(disp8.toHexString()).append("]");
    	info.append(",").append(opcodeLst.get(3).toString());
    	return info.toString();
    }
    
    private String opcode_$reg_Ib(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(1).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte ib=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(dwordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
    	info.append(",").append(ib.toHexString());
    	return info.toString();
    }
    
    
    
    private String opcode_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(dwordptr).append("ds:").append("[");
    	info.append(dis32.toHexString()).append("]");
    	return info.toString();
    }
    
 
    
    
    
    private String opcode_disp32_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(dwordptr).append("ds:").append("[").append(dis32.toHexString()).append("]");
    	info.append(",");
    	info.append(opcodeLst.get(2));
    	return info.toString();
    }
    
    private String opcode_disp32_Iz(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
        DWord iz=new DWord(frameLst,index+4);
        StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(dwordptr).append("ds:").append("[").append(dis32.toHexString()).append("]");
    	info.append(",");
    	info.append(iz.toHexString());
    	return info.toString();
    }
    

    
   
    
    
    private String opcode_byteptr_$reg_disp8_nop(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        return info.toString();
    }
    
   private String opcode_byteptr_$reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	UByte disp8=new UByte(frameLst,index+1);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        info.append(",").append(disp8.toHexString());
    	return info.toString();
    }
    
    private String opcode_byteptr_$reg_disp8_Ib(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	UByte ib=new UByte(frameLst,index+1);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        info.append(",").append(ib.toHexString());
    	return info.toString();
    }
    
    private String opcode_byteptr_$regreg_regl(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String regAll=opcodeLst.get(2).toString();
    	String reg1=regAll.substring(1,4);
    	String reg2=regAll.substring(4);
    	String sectionName=getSectionAddressName(reg1);
        
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(reg1).append("+").append(reg2).append("]");
        info.append(",").append(opcodeLst.get(3));
    	return info.toString();
    }
    
    //-------------------------wordptr-------------------------------------------------------------//
    
    private String opcode_wordptr_$reg_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(wordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("]");
    	info.append(",").append(opcodeLst.get(3).toString());
    	return info.toString();
    }
    
    private String opcode_wordptr_$reg_disp8_reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(wordptr).append(sectionName).append(":");
    	info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
    	info.append(",").append(opcodeLst.get(4).toString());
    	return info.toString();
    }
    
    private String opcode_wordptr_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(wordptr).append("ds:").append("[");
    	info.append(dis32.toHexString()).append("]");
    	return info.toString();
    }
    
    private String opcode_wordptr_disp32_Ib(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
        UByte ib=new UByte(frameLst,index+4);
        StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(wordptr).append("ds:").append("[").append(dis32.toHexString()).append("]");
    	info.append(",");
    	info.append(ib.toHexString());
    	return info.toString();
    }
    
    private String opcode_wordptr_disp32_Iz(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        DWord dis32=new DWord(frameLst,index);
        Word iz=new Word(frameLst,index+4);
        StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(wordptr).append("ds:").append("[").append(dis32.toHexString()).append("]");
    	info.append(",");
    	info.append(iz.toHexString());
    	return info.toString();
    }
    

    private String opcode_wordptr_$reg_disp8_Iw(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(2).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	Word iw=new Word(frameLst,index+1);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
        info.append(wordptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        info.append(",").append(iw.toHexString());
    	return info.toString();
    }
    
    
    
    private String opcode_regl_Ib(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
        int index=getCurrenIndex(parameterObj);
        UByte ib=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
    	info.append(opcodeLst.get(1).toString()).append(",");
    	info.append(ib.toHexString());
    	return info.toString();
    }
    
    
    private String opcode_regl_regl(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
     	info.append(opcodeLst.get(1)).append(",");
     	info.append(opcodeLst.get(2)).append("");
        return info.toString();
    }
    
    
    private String opcode_regl_byteptr_$reg(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(3).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
     	info.append(opcodeLst.get(1)).append(",");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("]");
        return info.toString();
    }
    
    private String opcode_regl_byteptr_$reg_disp8(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(3).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	UByte opand1=new UByte(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
     	info.append(opcodeLst.get(1)).append(",");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(opand1.toHexString()).append("]");
        return info.toString();
    }
    
    private String opcode_regl_byteptr_$reg_disp32(List opcodeLst,List<Integer> frameLst,CpuObject parameterObj){
    	String secondRegName=opcodeLst.get(3).toString();
    	secondRegName=secondRegName.substring(1);
    	String sectionName=getSectionAddressName(secondRegName);
    	int index=getCurrenIndex(parameterObj);
    	DWord disp32=new DWord(frameLst,index);
    	StringBuffer info=new StringBuffer();
    	info.append(opcodeLst.get(0)).append(" ");
     	info.append(opcodeLst.get(1)).append(",");
        info.append(byteptr).append(sectionName).append(":");
        info.append("[").append(secondRegName).append("+").append(disp32.toHexString()).append("]");
        return info.toString();
    }
    
    
    
    
    private void test_1(){
    	String str="mov_edx_$eax_disp8";
    	List<Integer> frameLst=new ArrayList<Integer>();
        PrefixParser prefixParser=(PrefixParser)GetACT.getBean("prefixParser");
        getOpcode(str,frameLst,prefixParser);
    }
    
    public static void main(String[] args) {
    	GetACT.getApplicationContext();
    	CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
//		logger.info(createOpcodeStr.getOpcode(orgStr, frameLst, parameterObj));
    	createOpcodeStr.test_1();
	}
}
