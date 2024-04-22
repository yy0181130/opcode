package cpu.aid;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import util.ArrayUtil;
import util.GetACT;
import util.Loader;

import com.google.common.collect.ImmutableMap;

import cpu.CpuEngine;
import cpu.bean.OneByteOpcodeBean;
import cpu.bean.OneByteOpcodeBeanLst;
import cpu.parent.CpuObject;
import cpu.status.CpuStatus;

@Service
public class FirstByteParser extends CpuObject{
    private ImmutableMap.Builder<Integer,OneByteOpcodeBean> oneByteMapBuilder;
    private ImmutableMap<Integer,OneByteOpcodeBean> oneByteMap;
	
    public FirstByteParser() {
    	oneByteMapBuilder=ImmutableMap.builder();
        getOneByteOpcode();
	}
    
    private void getOneByteOpcode(){
 	   OneByteOpcodeBeanLst oneLst =(OneByteOpcodeBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"oneByteOpcode.xml",OneByteOpcodeBeanLst.class);
 		List<OneByteOpcodeBean> oneByteLst=oneLst.getOpcodeLst();
 		for (OneByteOpcodeBean oneByteOpcodeBean : oneByteLst) {
 			String opcodeStr=oneByteOpcodeBean.getOpcode();
 			int opcode=Integer.parseInt(opcodeStr,16);
 			oneByteMapBuilder.put(opcode,oneByteOpcodeBean);
 		}
 		oneByteMap=oneByteMapBuilder.build();
 		
 	}
    
	@Override
	public void decode(List<Integer> frameLst, CpuObject cpuObj) {
		int index=getCurrenIndex(cpuObj);
		int typeIns= frameLst.get(index);
		addCurrenIndex(cpuObj);
		
		OneByteOpcodeBean oneByteOpcodeBean=oneByteMap.get(typeIns);
		OneByteOpcodeBean oneByteOpcodeBeanTemp=oneByteOpcodeBean.clone();
		cpuObj.addBean(oneByteOpcodeBeanTemp);
	    decode_instruct(frameLst,cpuObj,oneByteOpcodeBean);
	}
	
  	private void test_1(){
  		LinkedList<Integer> frameLst =new LinkedList<Integer>();
  		byte[] frameBytes = new byte[] { (byte)0x53 };
  		ArrayUtil.addByteToLinkedLst(frameBytes,frameLst);
  		decode(frameLst,this);
  	}
  	
  	public static void main(String[] args) {
  		FirstByteParser f=(FirstByteParser)GetACT.getBean("firstByteParser");
  		f.test_1();
	}
	
}
