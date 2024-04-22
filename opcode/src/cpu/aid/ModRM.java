package cpu.aid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import structs.DWord;
import structs.SByte;
import util.ByteUtil;
import util.GetACT;
import util.Loader;
import cpu.CpuEngine;
import cpu.bean.ModRM32BitBean;
import cpu.bean.ModRM32BitBeanLst;
import cpu.bean.ModRMBean;
import cpu.operand.bean.OperandContextBean;
import cpu.operand.choose.OperandType;
import cpu.parent.CpuObject;
import cpu.register.Register;
/**
 * 要确定出来用什么指令，有什么参数
 * @author youy
 *
 */
@Service
public class ModRM extends CpuObject{
	 private  ModRM32BitBeanLst mod32BitBeanLst;
	 private  Map<Integer,ModRM32BitBean> modrmMap;
	 
	 public ModRM(){
		 mod32BitBeanLst =(ModRM32BitBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"modrm32bit.xml",ModRM32BitBeanLst.class);
		 List<ModRM32BitBean>  modrm32Lst= mod32BitBeanLst.getModRm32BitBeanLst();
		 modrmMap=new HashMap<Integer,ModRM32BitBean>();
		 for (ModRM32BitBean modRM32BitBean : modrm32Lst) {
		  Integer hexDecimal=Integer.parseInt(modRM32BitBean.getHexadecimal(),16);
		  modrmMap.put(hexDecimal, modRM32BitBean);
		 }
	 }
	 
	public ModRMBean  setModRm(List<Integer> frameLst,CpuObject cpuObj) {
		Integer modrm=frameLst.get(getCurrenIndexAdd(cpuObj));
		byte valueByte = (byte) (modrm& 0xff);
		// 将字节转换为0/1字符串
		String binaryString = ByteUtil.toBinaryString(valueByte);
		String mod=binaryString.substring(0,2);
	    String reg=binaryString.substring(2,5);
		String rm=binaryString.substring(5,8);
		ModRMBean modRMBean=new ModRMBean(mod,reg,rm);
		modRMBean.setModrmValue(modrm);
		cpuObj.addBean(modRMBean);
		return modRMBean;
	}
	
	
	public String getEffectiveAddress(ModRMBean modRBean){
		ModRM32BitBean modBean=modrmMap.get(modRBean.getModrmValue());
		return modBean.getEffectiveAddress();
	}
	
     public String getR32(ModRMBean modRBean){
		ModRM32BitBean modBean=modrmMap.get(modRBean.getModrmValue());
		String r32=modBean.getR32();
		return r32;
	}
     
     public String getR8(ModRMBean modRBean){
		ModRM32BitBean modBean=modrmMap.get(modRBean.getModrmValue());
		String r8=modBean.getR8();
		return r8;
	}
     
   
    public String decode_getOperand(List<Integer> frameLst, CpuObject parameterObj,String effAddress){
	     StringBuffer medName=new StringBuffer();
	     ModRMBean modRMBean=(ModRMBean)parameterObj.getParameter(ModRMBean.class.getSimpleName());
	     if(modRMBean.getMod().equals("11")){
	    	 OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    	 operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	    	 OperandContextBean contextBean=new OperandContextBean();
	    	 
	    	 
	    	 contextBean.setRegister(Register.getRegisterByName(effAddress));
	    	 operandIo.setOperanadContext(parameterObj,contextBean);
	         return null;
	     }
	     OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
		 operandIo.setOperandType(parameterObj, OperandType.PointType);
		 OperandContextBean contextBean=new OperandContextBean();
	     boolean isSibCode=isHaveSib(effAddress,frameLst,parameterObj,contextBean);
	     if(!isSibCode)isHaveAddress(effAddress,frameLst,parameterObj,contextBean);
		isHaveDisp8Or32(frameLst,parameterObj,effAddress,contextBean);
		operandIo.setOperanadContext(parameterObj, contextBean);
		return medName.toString();
	}
    
  
  private boolean isHaveSib(String effectiveAddress,List<Integer> frameLst, CpuObject parameterObj,OperandContextBean contextBean){
		if(effectiveAddress.contains("[--][--]")){
			Sib sib=(Sib)GetACT.getBean("sib");
		    sib.decode_sib(frameLst, parameterObj,contextBean);
			return true;
		}
		return false;
    }
  
  private boolean isHaveAddress(String effectiveAddress,List<Integer> frameLst, CpuObject parameterObj,OperandContextBean contextBean){
		if(effectiveAddress.contains("[e")){
		    contextBean.setSibBase(Register.getRegisterByName(effectiveAddress.substring(1, 4)));
			return true;
		}
		return false;
  }
  
   private boolean isHaveDisp8Or32(List<Integer> frameLst,CpuObject parameterObj,String effectiveAddress,OperandContextBean contextBean){
	     if(effectiveAddress.contains("disp8")){
	 		SByte opand=new SByte(frameLst,getCurrenIndex(parameterObj)); 
	 	    addCurrenIndex(parameterObj,opand.getSize());//增加指令的长度
	 		contextBean.setDisplacement(opand);
			return true;
		}
		else if(effectiveAddress.contains("disp32")){
			DWord opand=new DWord((ArrayList<Integer>)frameLst,getCurrenIndex(parameterObj));
			addCurrenIndex(parameterObj,opand.getSize());//增加指令的长度
		
			contextBean.setDisplacement(opand);
			return true;
		}
		return false;
   }
  
	private void test_getinfo(){
		ModRM modRm=(ModRM)GetACT.getBean("modRM");
		//modRm.setModRm(0xC4, this);
    }
	
	public static void main(String[] args) {
		ModRM d=(ModRM)GetACT.getBean("modRM");
		d.test_getinfo();
	}
}
