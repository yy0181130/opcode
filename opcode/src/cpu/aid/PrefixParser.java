package cpu.aid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cpu.bean.InstructParserBean;
import cpu.bean.PrefixBean;
import cpu.parent.CpuObject;
import cpu.register.OperandSize;
import cpu.register.SegementRegister;

@Service
public class PrefixParser extends CpuObject{
	private List<Integer> defaultPrefixLst;
	
	public PrefixParser() {
		defaultPrefixLst=new ArrayList<Integer>();
		defaultPrefixLst.add(0x66);
		defaultPrefixLst.add(0x67);
		defaultPrefixLst.add(0x2e);
		defaultPrefixLst.add(0x3e);
		defaultPrefixLst.add(0x26);
		defaultPrefixLst.add(0x64);
		defaultPrefixLst.add(0x65);
		defaultPrefixLst.add(0x36);
		defaultPrefixLst.add(0xf3);
		defaultPrefixLst.add(0xf2);
		//defaultPrefixLst.add(0xf0);
	}
	@Override
	public void decode(List<Integer> frameLst, CpuObject cpuObj) {
		PrefixBean prefixBean=new PrefixBean();
        cpuObj.addBean(prefixBean);
        doInsertPrefix(frameLst,cpuObj);
	}
	
	
	private void doInsertPrefix(List<Integer> frameLst,CpuObject cpuObj){
		Integer code=frameLst.get(getCurrenIndex(cpuObj));
		if(!defaultPrefixLst.contains(code))return;
	       //frameLst.remove(0);
		switch(code){
		case 0x66:overrideOperandSize(cpuObj);break;
		case 0x64:setSegementRegister(cpuObj);break;
		case 0xf2:repne(cpuObj);break;
		case 0xf3:rep(cpuObj);break;
		}
	    	PrefixBean prefixBean=(PrefixBean)cpuObj.getParameter(PrefixBean.class.getSimpleName());
	    	prefixBean.addPrefix(code);
	    	addCurrenIndex(cpuObj);
	    	doInsertPrefix(frameLst,cpuObj);
	
	}
	
	private void overrideOperandSize(CpuObject cpuObj){
		InstructParserBean prefixBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		prefixBean.getEffectiveOperandSize().setEffectiveSize(OperandSize.wordptr);
	
	}
	
   private void repne(CpuObject cpuObj){
	        InstructParserBean prefixBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
	        prefixBean.setRepneFlag(true);
	 }
	
   private void rep(CpuObject cpuObj){
       InstructParserBean prefixBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
       prefixBean.setRepFlag(true);
}
   
	
	
	private void setSegementRegister(CpuObject cpuObj){
		InstructParserBean prefixBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		prefixBean.setSegementRegister(SegementRegister.fs);
	}
	
 }
