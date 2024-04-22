package cpu.instructions;

import java.util.List;

import memory.util.MemoryAddressUtil;

import org.springframework.stereotype.Component;

import structs.DWord;
import structs.SByte;
import cpu.parent.CpuObject;
import cpu.util.CpuUtil;

@Component
public class Jcc extends CpuObject{
    public void jcc_nz_ne(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jne short "+jumpAddress);
    }
    
    public void jcc_s(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip, jnzLength+2);
    	logger.info("js "+jumpAddress);
    }
    
    public void jcc_z_e(List<Integer> frameLst,CpuObject parameterObj){
        SByte jnzLength=new SByte(frameLst.get(1));
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddInt(eip, jnzLength.toDecValue()+2);
    	logger.info("je short "+jumpAddress);
    }
    
    public void jcc_ns(List<Integer> frameLst,CpuObject parameterObj){
        SByte jnzLength=new SByte(frameLst,1);
    	//Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddInt(eip, jnzLength.toDecValue()+2);
    	logger.info("jns short "+jumpAddress);
    }
    
    public void jcc_nb_ae_nc(List<Integer> frameLst,CpuObject parameterObj){
      	Integer jnzLength=frameLst.get(1);
      	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddInt(eip, jnzLength+2);
    	logger.info("jnb short "+jumpAddress);
    }
    
    public void jcc_ae_nb_nc(List<Integer> frameLst,CpuObject parameterObj){
    	int index=getCurrenIndex(parameterObj);
        DWord code=new DWord(frameLst,index);
    	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("jnb "+jumpAddress);
    }
    
    
    public void jcc_e_z(List<Integer> frameLst,CpuObject parameterObj){
    	int index=getCurrenIndex(parameterObj);
    	DWord code=new DWord(frameLst,index);
     	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("je  "+jumpAddress);
    }
    
    public void jcc_ne_nz(List<Integer> frameLst,CpuObject parameterObj){
    	int index=getCurrenIndex(parameterObj);
    	DWord code=new DWord(frameLst,index);
    	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("jne  "+jumpAddress);
    }
    
    
  
    
    public void jcc_nbe_a(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("ja short "+jumpAddress);
    }
    public void jcc_a_nbe(List<Integer> frameLst,CpuObject parameterObj){
    	DWord code=new DWord(frameLst,getCurrenIndex(parameterObj));
    	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("ja  "+jumpAddress);
    }
    public void jcc_nl_ge_2(List<Integer> frameLst,CpuObject parameterObj){
        DWord code=new DWord(frameLst,getCurrenIndex(parameterObj));
        addCurrenIndex(parameterObj,4);
        String eip=CpuUtil.getEip();
        String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
        String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
        logger.info("jge  "+jumpAddress);
    }
    
    
    
    public void jcc_be_na(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jbe short "+jumpAddress);
    }
    
    public void jcc_g(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jg short "+jumpAddress);
    }
    
    public void jcc_b_chae(List<Integer> frameLst,CpuObject parameterObj){
    	int index=getCurrenIndex(parameterObj);
    	DWord code=new DWord(frameLst,index);
    	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("jb  "+jumpAddress);
    }
    
    public void jcc_b_nae_c(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jb short "+jumpAddress);
    }
    
    
    public void jcc_l_nge(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jl short "+jumpAddress);
    }
    
    public void jcc_l_nge_long(List<Integer> frameLst,CpuObject parameterObj){
    	int index=getCurrenIndex(parameterObj);
    	DWord code=new DWord(frameLst,index);
    	addCurrenIndex(parameterObj,4);
    	String eip=CpuUtil.getEip();
    	String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
    	String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
    	logger.info("jl "+jumpAddress);
    }
    
    public void jcc_nl_ge(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jge short "+jumpAddress);
    }
    
    public void jcc_le_ng(List<Integer> frameLst,CpuObject parameterObj){
    	Integer jnzLength=frameLst.get(1);
    	addCurrenIndex(parameterObj);
    	String eip=CpuUtil.getEip();
    	String jumpAddress=MemoryAddressUtil.addressAddOnlLastByte(eip, jnzLength+2);
    	logger.info("jle short "+jumpAddress);
    }
    
    public void jcc_le_ng_2(List<Integer> frameLst,CpuObject parameterObj){
        DWord code=new DWord(frameLst,getCurrenIndex(parameterObj));
        addCurrenIndex(parameterObj,4);
        String eip=CpuUtil.getEip();
        String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
        String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
        logger.info("jge  "+jumpAddress);
    }
    
    public void jcc_nle_g(List<Integer> frameLst,CpuObject parameterObj){
        DWord code=new DWord(frameLst,getCurrenIndex(parameterObj));
        addCurrenIndex(parameterObj,4);
        String eip=CpuUtil.getEip();
        String address=MemoryAddressUtil.addressAdd(code.toHexString(), 6);
        String jumpAddress=MemoryAddressUtil.addressAdd(eip,address);
        logger.info("jg  "+jumpAddress);
    }
    
    
    
}
