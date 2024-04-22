package cpu.aid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import util.Loader;
import cpu.CpuEngine;
import cpu.bean.TwoByteOpcodeBean;
import cpu.bean.TwoByteOpcodeBeanLst;
import cpu.parent.CpuObject;

@Service
public class TwoByteParser extends CpuObject{
	 private Map<Integer ,TwoByteOpcodeBean> twoOpcodeMap;
	 
	 public TwoByteParser() {
		 twoOpcodeMap=new HashMap<Integer ,TwoByteOpcodeBean>();
		 setTwoBeanMap();
	 }
	 
	 
	 private void setTwoBeanMap(){
		 TwoByteOpcodeBeanLst twoLst =(TwoByteOpcodeBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"twoByteOpcode.xml",TwoByteOpcodeBeanLst.class);
		 List<TwoByteOpcodeBean> twoBeanLst= twoLst.getTwoByteOpcodeLst();
		 for (TwoByteOpcodeBean twoByteOpcodeBean : twoBeanLst) {
			 Integer opcode=Integer.parseInt(twoByteOpcodeBean.getOpcode(),16);
			 twoOpcodeMap.put(opcode, twoByteOpcodeBean);
		}
	 }
	 
	 @Override
	public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		int index=getCurrenIndexAdd(parameterObj);
		int opcode= frameLst.get(index);
		TwoByteOpcodeBean  twoByteOBean=twoOpcodeMap.get(opcode);
		parameterObj.addBean(twoByteOBean);
		decode_instruct(frameLst,parameterObj,twoByteOBean);
	}
 }
