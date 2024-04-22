package cpu.aid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import util.ArrayUtil;
import util.GetACT;
import util.Loader;
import cpu.CpuEngine;
import cpu.bean.GroupOpcodeBean;
import cpu.bean.GroupOpcodeBeanLst;
import cpu.bean.GroupOpcodeModRmBean;
import cpu.bean.ModRMBean;
import cpu.bean.OneByteOpcodeBean;
import cpu.parent.CpuBeanObj;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

@Service
public class Group extends CpuObject{
     private  GroupOpcodeBeanLst groupOpcodeLst;
	 private Map<String,GroupOpcodeBean> opcodeMap;
	 private Map<String,GroupOpcodeBean> opcodeMap2;//通过OPcode进行的遍历
  
	public Group() {
		groupOpcodeLst =(GroupOpcodeBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"groupOpcode.xml",GroupOpcodeBeanLst.class);
        opcodeMap=new HashMap<String,GroupOpcodeBean>();
        opcodeMap2=new HashMap<String,GroupOpcodeBean>();
		setOpcodeMap();
 	}
	
	private void setOpcodeMap(){
		 List<GroupOpcodeBean> groupOpcodeBeanLst=groupOpcodeLst.getGroupOpcodeBeanLst();
		 for (GroupOpcodeBean groupOpcodeBean : groupOpcodeBeanLst) {
			 opcodeMap.put(groupOpcodeBean.getGroup(), groupOpcodeBean);
			 opcodeMap2.put(groupOpcodeBean.getOpCode(), groupOpcodeBean);
		}
	}
	
	private String getInstruct(String groupNo,String encode,CpuObject parameterObj){
	    CpuBeanObj onebyteOpcodeBean=(CpuBeanObj) parameterObj.getParameter(OneByteOpcodeBean.class.getSimpleName());
		String opcode=onebyteOpcodeBean.getOpcode();
		GroupOpcodeBean groupOpcodeBean=opcodeMap2.get(opcode);
		if(groupOpcodeBean==null) groupOpcodeBean=opcodeMap.get(groupNo);
		List<GroupOpcodeModRmBean> modrmBeanLst=groupOpcodeBean.getModrmBeanLst();
		for (GroupOpcodeModRmBean groupOpcodeModRmBean : modrmBeanLst) {
			if(groupOpcodeModRmBean.getEncode().equals(encode)){
			
			  String first=groupOpcodeModRmBean.getFirstOperandType();
			  String second=groupOpcodeModRmBean.getSecondOperandType();
			  if(first!=null)onebyteOpcodeBean.modifyFirstOperandType(first);
			  if(second!=null)onebyteOpcodeBean.modifySecondOperandType(second);
			  return  groupOpcodeModRmBean.getInstruct();
			}
		}
		return null;
	}
	
	
    public void group1_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("1",modRBean.getReg(),cpuObj);
    	OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }
    
    public void group11_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("11",modRBean.getReg(),cpuObj);
        OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }
    public void group1a_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("1a",modRBean.getReg(),cpuObj);
        OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }
    
    public void group2_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("2",modRBean.getReg(),cpuObj);
    	OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }  
    
    public void group3_handler(List<Integer> frameLst, CpuObject cpuObj){
		ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("3",modRBean.getReg(),cpuObj);
        OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }  
    
    public void group4_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("4",modRBean.getReg(),cpuObj);
    	OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }  
    
    public void group5_handler(List<Integer> frameLst, CpuObject cpuObj){
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
    	modRm.setModRm(frameLst, cpuObj);
    	ModRMBean modRBean=(ModRMBean)cpuObj.getParameter(ModRMBean.class.getSimpleName());
    	String instruct=getInstruct("5",modRBean.getReg(),cpuObj);
    	OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
    	operandsParser.decode(instruct, frameLst, cpuObj);
    }
    
    private void test_1(){
    	Group g=(Group)GetACT.getBean("group");
    	List<Integer> frameLst=new ArrayList<Integer>();
        byte[] frameBytes = new byte[] { (byte)0x53 };
  		ArrayUtil.addByteToLinkedLst(frameBytes,frameLst);
    	//List<GroupOpcodeBean> l=gLst.getGroupOpcodeBean();
    }
    
	public static void main(String[] args) {
		Group a=new Group();
		a.test_1();
	}
}
