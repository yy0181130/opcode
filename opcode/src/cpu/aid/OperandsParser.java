package cpu.aid;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import structs.DWord;
import structs.SByte;
import structs.UByte;
import structs.Word;
import structs.operator.IOperatorNum;
import util.GetACT;
import util.ReflectUtil;
import cpu.bean.InstructParserBean;
import cpu.bean.ModRMBean;
import cpu.bean.OneByteOpcodeBean;
import cpu.bean.TwoByteOpcodeBean;
import cpu.operand.bean.OperandBean;
import cpu.operand.bean.OperandContextBean;
import cpu.operand.choose.OperandType;
import cpu.parent.CpuBeanObj;
import cpu.parent.CpuObject;
import cpu.register.OperandSize;
import cpu.register.Register;
import cpu.register.SegementRegister;
import cpu.status.CpuStatus;
import cpu.util.ModRmUtil;
import cpu.util.bean.ModRMUBean;

/**
 * 所有的操作数都在这里解析
 * 
 * @author youy
 * 
 */
@Service
public class OperandsParser extends CpuObject {
	private List<String> addressLst;

	public void decode(String opcode, List<Integer> frameLst,CpuObject parameterObj) {
		
		OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
		operandBean.setOpcode(opcode);
		 CpuBeanObj cpuBeanObj =null;
		 cpuBeanObj = (CpuBeanObj) parameterObj.getParameter(TwoByteOpcodeBean.class.getSimpleName());
		if(cpuBeanObj==null) cpuBeanObj = (CpuBeanObj) parameterObj.getParameter(OneByteOpcodeBean.class.getSimpleName());
		String firstOType = cpuBeanObj.getFirstOperandType();
		  setAddressOType(firstOType, frameLst, parameterObj);
		  
		String secondOType = cpuBeanObj.getSecondOperandType();
		if(!secondOType.equals(""))
		  setAddressOType(secondOType, frameLst, parameterObj);
		
		ShowAssembler showAssembler=(ShowAssembler)GetACT.getBean("showAssembler");
		showAssembler.show(parameterObj);
		
		DoOpcode doOpcode=(DoOpcode)GetACT.getBean("doOpcode");
		doOpcode.doOpcode(parameterObj);
	}
	
	
	public OperandsParser() {
	//	addressLst = new String[] { "AL","BL","Eb","Ev","Ew","Gb","Gv","Ib","I","r","O","M","1"};
		addressLst=new ArrayList<String>();
		Method[] method=this.getClass().getDeclaredMethods();
		for (int i = 0; i < method.length; i++){
			String meth = method[i].getName();
			if(!meth.contains("parser"))continue;
			String keyword=meth.split("_")[1];
			addressLst.add(keyword);
		}
	}

	protected void setCallBackMed(String addressMedAllValue,String addressMedType, List<Integer> frameLst,CpuObject parameterObj) {
		String thisMedName = "parser_" + addressMedType;
		Class<?>[] parameterType = new Class[] {String.class,List.class,CpuObject.class};
		Object[] parameter = new Object[] {addressMedAllValue, frameLst, parameterObj };
        ReflectUtil.invokeMethodHasParame(this, thisMedName, parameterType,parameter,parameterObj);
	}

	private String getAddressMed(String operandType) {
		for (int i = 0; i < addressLst.size(); i++) {
			if (operandType.startsWith(addressLst.get(i))) {
				return addressLst.get(i);
			}
		}
		return null;
	}

	private void setAddressOType(String oType, List<Integer> frameLst,CpuObject parameterObj) {
		String addressMed = getAddressMed(oType);
		setCallBackMed(oType,addressMed, frameLst, parameterObj);
	}
	
	  void parser_AL(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	 	OperandContextBean contextBean=new OperandContextBean();
		contextBean.setRegister(Register.getRegisterByName("al"));
	  	operandIo.setOperanadContext(parameterObj,contextBean);
	  	
    }
	
	  void parser_BL(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	 	OperandContextBean contextBean=new OperandContextBean();
		contextBean.setRegister(Register.getRegisterByName("bl"));
	  	operandIo.setOperanadContext(parameterObj,contextBean);
   }
	  
	 void parser_CL(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	        OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	        operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	        OperandContextBean contextBean=new OperandContextBean();
	        contextBean.setRegister(Register.getRegisterByName("cl"));
	        operandIo.setOperanadContext(parameterObj,contextBean);
	   }
	
	  void parser_Eb(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String effAddress = modRm.getEffectiveAddress(modRBean);
		 ModRMUBean rmUBean=ModRmUtil.getEffective(effAddress);
		 String r8Address=effAddress;
		 if(rmUBean!=null)  r8Address=rmUBean.getR8();
		 modRm.decode_getOperand(frameLst, parameterObj,r8Address);
		
	    OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandSize(parameterObj, OperandSize.byteptr.getOperandLength());
	 }
	
	//不需要计算长度
	  void parser_Ev(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String effAddress = modRm.getEffectiveAddress(modRBean);
		modRm.decode_getOperand(frameLst, parameterObj,effAddress);
	 }
	
	  void parser_Ew(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String effAddress = modRm.getEffectiveAddress(modRBean);
		ModRMUBean rmUBean=ModRmUtil.getEffective(effAddress);
		String r16=null;
		if(rmUBean==null){
			r16=effAddress;
		}
		else r16=rmUBean.getR16();
		modRm.decode_getOperand(frameLst, parameterObj,r16);
	    OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandSize(parameterObj, OperandSize.wordptr.getOperandLength());
    }	
	
	
	 void parser_Gb(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String r8 = modRm.getR8(modRBean);
		
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	 	OperandContextBean contextBean=new OperandContextBean();
		contextBean.setRegister(Register.getRegisterByName(r8));
	  	operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	 void parser_Gv(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String r32 = modRm.getR32(modRBean);
	    
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	 	OperandContextBean contextBean=new OperandContextBean();
		contextBean.setRegister(Register.getRegisterByName(r32));
	  	operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	
	 void parser_r(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
         String reg="e"+addressType.substring(1,addressType.length()).toLowerCase();
         OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	     operandIo.setOperandType(parameterObj, OperandType.RegisterType);
	 	 OperandContextBean contextBean=new OperandContextBean();
	 	 contextBean.setRegister(Register.getRegisterByName(reg));
	  	 operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	 void parser_Ib(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
    	//medBuf.append(addressType);
		SByte opand=new SByte(frameLst,getCurrenIndex(parameterObj));   
		addCurrenIndex(parameterObj);
   	   OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
   	   operandIo.setOperandType(parameterObj, OperandType.ImmediateType);
       OperandContextBean contextBean=new OperandContextBean();
	   contextBean.setImmediate(opand);
	   operandIo.setOperanadContext(parameterObj,contextBean);
	}
	 void parser_Iz(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		InstructParserBean instructParserBean = (InstructParserBean) parameterObj.getParameter(InstructParserBean.class.getSimpleName());
		OperandSize operandSize= instructParserBean.getEffectiveOperandSize().getEffectiveSize();
		IOperatorNum opand=null;
		if(operandSize==OperandSize.dwordptr){
			opand=new DWord(frameLst,getCurrenIndex(parameterObj));  
			addCurrenIndex(parameterObj,4);
		}else if(operandSize==OperandSize.wordptr){
			opand=new Word(frameLst,getCurrenIndex(parameterObj));  
			addCurrenIndex(parameterObj,2);
		}
	   OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
   	   operandIo.setOperandType(parameterObj, OperandType.ImmediateType);
       OperandContextBean contextBean=new OperandContextBean();
	   contextBean.setImmediate(opand);
	   operandIo.setOperanadContext(parameterObj,contextBean);
	}
	 void parser_Iv(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
    	//medBuf.append(addressType);
		DWord opand=new DWord(frameLst,getCurrenIndex(parameterObj));   
		addCurrenIndex(parameterObj,opand.getSize());
		
	   OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
   	   operandIo.setOperandType(parameterObj, OperandType.ImmediateType);
       OperandContextBean contextBean=new OperandContextBean();
	   contextBean.setImmediate(opand);
	   operandIo.setOperanadContext(parameterObj,contextBean);
	}

	
	 void parser_Iw(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		Word opand=new Word(frameLst,getCurrenIndex(parameterObj));   
		addCurrenIndex(parameterObj,opand.getSize());
		
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.ImmediateType);
	    OperandContextBean contextBean=new OperandContextBean();
		contextBean.setImmediate(opand);
		operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	
	 void parser_M(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String effectiveAddress = modRm.getEffectiveAddress(modRBean);
		modRm.decode_getOperand(frameLst, parameterObj,effectiveAddress);
	}
	
	
	 void parser_Ob(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		  DWord address=new DWord(frameLst,getCurrenIndex(parameterObj));
		  addCurrenIndex(parameterObj,address.getSize());//增加指令的长度
		  
		  OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	      operandIo.setOperandType(parameterObj, OperandType.PointType);
	      operandIo.setOperandSize(parameterObj, 8);
	 	  OperandContextBean contextBean=new OperandContextBean();
	 	  contextBean.setDisplacement(address);
	  	  operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	 void parser_Ov(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		  DWord address=new DWord(frameLst,getCurrenIndex(parameterObj));
		  addCurrenIndex(parameterObj,address.getSize());//增加指令的长度
		  
		  OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	      operandIo.setOperandType(parameterObj, OperandType.PointType);
	 	  OperandContextBean contextBean=new OperandContextBean();
	 	  contextBean.setDisplacement(address);
	  	  operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	
	 void parser_Sw(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
	    ModRM modRm = (ModRM) GetACT.getBean("modRM");
		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
		if (modRBean == null)modRBean=modRm.setModRm(frameLst, parameterObj);
		String reg=modRBean.getReg();
		SegementRegister segReg=SegementRegister.getSegByCode(reg);
		
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.SegementRegisterType);
	 	OperandContextBean contextBean=new OperandContextBean();
		contextBean.setSegementRegister(segReg);
	  	operandIo.setOperanadContext(parameterObj,contextBean);
	}
	
	
	 void parser_1(String addressType,List<Integer> frameLst, CpuObject parameterObj) {
		UByte opand=new UByte(1);
		OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
	    operandIo.setOperandType(parameterObj, OperandType.ImmediateType);
	    OperandContextBean contextBean=new OperandContextBean();
		contextBean.setImmediate(opand);
		operandIo.setOperanadContext(parameterObj,contextBean);
	}
	

   public static void main(String[] args) {
	   OperandsParser o=new OperandsParser();
   }
}
