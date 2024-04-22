package cpu.parent;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import util.GetACT;
import util.ReflectUtil;
import cpu.aid.CreateOpcodeStr;
import cpu.bean.InstructParserBean;
import cpu.bean.PrefixBean;
import cpu.status.CpuStatus;

public abstract class CpuObject {
	public static Logger logger = LogManager.getLogger(CpuObject.class);
	private CpuStatus cpuStatus;
	private  boolean doOpcode=false;
	
    public boolean isDoOpcode() {
        return doOpcode;
    }

    public void setCpuStatus(CpuStatus cpuStatus) {
		this.cpuStatus = cpuStatus;
	}
	
	public CpuStatus getCpuStatus() {
		return  cpuStatus; 
	}

	private boolean isFindError;

	
	public boolean isFindError() {
		return isFindError;
	}

	public void setFindError(boolean isFindError) {
		this.isFindError = isFindError;
	}

	// 解码
	public void decode(List<Integer> frameLst,CpuObject parameterObj){}
	//指令的定位
	public void instructionLocate(){}
	//指令的执行
	public void doOpcode(CpuObject parameterObj){
		//logger.info("----待实现-----------");
	};
	public void setEipOfSize(int codeSize){
		cpuStatus.setEipOfSize(codeSize);
	}
	
	//--------------------------参数相关--------------------------/
	// 存放参数的list
	private List<CpuBeanObj> cpuBeanObjLst=new ArrayList<CpuBeanObj>();

	public void addBean(CpuBeanObj bean) {
		   cpuBeanObjLst.add(bean);
	}

	public void clearParameterList() {
			cpuBeanObjLst.clear();
		}
	
	public void setInstructLength(CpuObject parameterObj,int length){
		 InstructParserBean instructBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
		 PrefixBean prefixBean=(PrefixBean)parameterObj.getParameter(PrefixBean.class.getSimpleName()); 
		 int prefixSize= prefixBean.getPrefixLst().size();
		 instructBean.setCurrentIndex(length+prefixSize);
	}

  public void addInstructLength(CpuObject parameterObj,int length){
	  InstructParserBean instructBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
	  int currentLength=instructBean.getCurrentIndex();
	  instructBean.setCurrentIndex(currentLength+length);
  }
	
	
	public CpuBeanObj getParameter(String className) {
			for (int i = 0; i < cpuBeanObjLst.size(); i++) {
				CpuBeanObj obj = cpuBeanObjLst.get(i);
				String pathName = obj.getClass().getSimpleName();
				if (pathName.equals(className.trim()))
					return obj;
			}
			return null;
    }	
	
	
	protected void allocatInstruct(){
		
	}
	
	public void addCurrenIndex(CpuObject cpuObj){
		InstructParserBean iBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		iBean.setCurrentIndex(iBean.getCurrentIndex()+1);
	}
	
	protected void addCurrenIndex(CpuObject cpuObj,int instructLength){
		InstructParserBean iBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		iBean.setCurrentIndex(iBean.getCurrentIndex()+instructLength);
	}
	
	protected int getCurrenIndex(CpuObject cpuObj){
		InstructParserBean iBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		return iBean.getCurrentIndex();
	}
	
	protected int getCurrenIndexAdd(CpuObject cpuObj){
		InstructParserBean iBean=(InstructParserBean)cpuObj.getParameter(InstructParserBean.class.getSimpleName());
		int currentIndex=iBean.getCurrentIndex();
		iBean.setCurrentIndex(iBean.getCurrentIndex()+1);
		return currentIndex;
	}
	
	protected void setCallBackMed(String instructName,String medName,List<Integer> instructLst,CpuObject parameterObj) {
		CpuObject cpuobj=(CpuObject)GetACT.getBean(instructName);
		Class<?> clazz = cpuobj.getClass();
  		String classNAME = clazz.getCanonicalName();
  		Object[] parameter = new Object[] {instructLst,parameterObj};
  		Class<?>[] parameterClass = new Class[] {List.class,CpuObject.class};
  		ReflectUtil.invokeMethodWithObjHasSpecialParame(classNAME, cpuobj, medName, parameter, parameterClass,parameterObj);
  	}
	
	
	protected void decode_instruct(List<Integer> frameLst, CpuObject cpuObj,CpuBeanObj cpuBeanObj){
		setCallBackMed(cpuBeanObj.getOpcodeName(),cpuBeanObj.getHandler(),frameLst,cpuObj);
	}
	
	
	
	protected String getLogInfo(String codeMedName,List<Integer> frameLst,CpuObject parameterObj){
		CreateOpcodeStr createOpcodeStr=(CreateOpcodeStr)GetACT.getBean("createOpcodeStr");
		String opcodeStr=createOpcodeStr.getOpcode(codeMedName, frameLst, parameterObj);
		return opcodeStr;
		
	}
	
	
	
}
