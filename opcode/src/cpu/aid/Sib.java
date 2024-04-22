package cpu.aid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import structs.DWord;
import util.ByteUtil;
import util.GetACT;
import util.Loader;
import cpu.CpuEngine;
import cpu.bean.InstructParserBean;
import cpu.bean.ModRMBean;
import cpu.bean.Sib32BitBean;
import cpu.bean.Sib32BitBeanLst;
import cpu.bean.SibBean;
import cpu.operand.bean.OperandContextBean;
import cpu.parent.CpuObject;
import cpu.register.Register;

@Service
public class Sib extends CpuObject{
	 private  Sib32BitBeanLst sib32BitBeanLst;
	 private  Map<Integer,Sib32BitBean> sib32Map;
	 
	 public Sib(){
		 sib32BitBeanLst =(Sib32BitBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"sib32bit.xml",Sib32BitBeanLst.class);
		 List<Sib32BitBean>  sib32bitLst= sib32BitBeanLst.getSib32BitBeanLst();
		 sib32Map=new HashMap<Integer,Sib32BitBean>();
		 for (Sib32BitBean sib32BitBean : sib32bitLst) {
		  Integer hexDecimal=Integer.parseInt(sib32BitBean.getHexadecimal(),16);
		  sib32Map.put(hexDecimal, sib32BitBean);
		 }
	 }
	 
	private SibBean  setModRm(List<Integer> frameLst,CpuObject parameterObj) {
		Integer sibHexValue=frameLst.get(getCurrenIndexAdd(parameterObj));
		byte valueByte = (byte) (sibHexValue& 0xff);
		// 将字节转换为0/1字符串
		String binaryString = ByteUtil.toBinaryString(valueByte);
		String ss=binaryString.substring(0,2);
	    String index=binaryString.substring(2,5);
		String base=binaryString.substring(5,8);
		SibBean sibBean=new SibBean(ss,base,index,sibHexValue);
		parameterObj.addBean(sibBean);
		return sibBean;
	}
	
	public void decode_sib(List<Integer> frameLst, CpuObject parameterObj,OperandContextBean contextBean){
		SibBean sibBean=setModRm(frameLst,parameterObj);
		Sib32BitBean sib32BitBean=sib32Map.get(sibBean.getHexCode());
	    getBase(sib32BitBean,parameterObj,contextBean);
     }
	
	private void  getBase(Sib32BitBean sib32BitBean,CpuObject parameterObj,OperandContextBean contextBean){
		//需要处理[*]的情况
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
	    getScaledIndex(sib32BitBean,contextBean);
	    if(!sib32BitBean.getR32().equals("[*]")){
			contextBean.setSibBase(Register.getRegisterByName(sib32BitBean.getR32()));
			return;
		}
	   if(modRBean.getMod().equals("00")){
			InstructParserBean instructBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
			DWord address=new DWord((ArrayList<Integer>)instructBean.getFrameLst(),getCurrenIndex(parameterObj));
		    addCurrenIndex(parameterObj,address.getSize());//增加指令的长度
		    contextBean.setDisplacement(address);
			return ;
		}
	   else if(modRBean.getMod().equals("01")){
			contextBean.setSibBase(Register.getRegisterByName("ebp"));
			return ;
			}
	   else if(modRBean.getMod().equals("10")){
		   contextBean.setSibBase(Register.getRegisterByName("ebp"));
		   return ;
		}
	  
		return ;
	}
	private void  getScaledIndex(Sib32BitBean sib32BitBean,OperandContextBean contextBean){
		String sacledIndex=sib32BitBean.getSacledIndex(); 
		if(sacledIndex.equals("none")){
			return ;
		}
        String si=sib32BitBean.getSacledIndex();
		si=si.substring(1,si.length()-1);
		String value[]=si.split("\\*");
		contextBean.setSibIndex(Register.getRegisterByName(value[0]));
		if(value.length>1)
		contextBean.setSibScale(Integer.parseInt(value[1]));
	}
	
	public static void main(String[] args) {
		Sib d=(Sib)GetACT.getBean("sib");
	}
}
