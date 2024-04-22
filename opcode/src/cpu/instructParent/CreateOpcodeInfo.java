package cpu.instructParent;

import java.util.ArrayList;
import java.util.List;

import util.ReflectUtil;

import cpu.bean.CreateOpcodeStrBean;
import cpu.parent.CpuObject;

public abstract class CreateOpcodeInfo extends CpuObject {
	protected String dwordptr = "dword ptr ";
	protected String wordptr = "word ptr ";
	protected String byteptr = "byte ptr ";

	protected String setMedCall(String medName, List<String> opcodeLst,
			List<Integer> frameLst, CpuObject parameterObj) {
		Class<?>[] parameterType = new Class[] { List.class, List.class,
				CpuObject.class };
		Object[] parameter = new Object[] { opcodeLst, frameLst, parameterObj };
		return (String) ReflectUtil.invokeMethodHasParame(this, medName,
				parameterType, parameter,parameterObj);
	}

	public String getOpcode(String orgStr, List<Integer> frameLst,
			CpuObject parameterObj) {
		List<String> opcodeLst = new ArrayList<String>();
		String medName = getMedName(orgStr, opcodeLst);
		String logInfo = setMedCall(medName, opcodeLst, frameLst, parameterObj);
		return logInfo;
	}

	protected String getMedName(String orgStr, List<String> opcodeLst) {
		StringBuffer medStr = new StringBuffer();
		medStr.append("opcode");
		String[] orgStrArray = orgStr.split("_");
		opcodeLst.add(orgStrArray[0]);
		for (int i = 1; i < orgStrArray.length; i++) {
			medStr.append("_");
			String medName = orgStrArray[i];
			opcodeLst.add(medName);
			if (medName.contains("$")) {
				medStr.append("$");
				medName = medName.substring(1);
			};
			String medValue = CreateOpcodeStrBean.getValue(medName);
			medStr.append(medValue);
		}
		return medStr.toString();
	}

	private void splitRegLst(){
		
	}
	
	protected String getSectionAddressName(String reg) {
		String sectionName = CreateOpcodeStrBean.getSectionAddressValue(reg);
		return sectionName;
	}

}
