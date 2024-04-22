package cpu.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CreateOpcodeStrBean {
	private static Map<String,String> typeMappingMap;
	private static Map<String,String> sectionAddressMap;
	
	static{
		init();
		initSectionAddress();
	}
   private static void initSectionAddress(){
	    sectionAddressMap=new HashMap<String,String>();
	    sectionAddressMap.put("eax", "ds");
	    sectionAddressMap.put("ecx", "ds");
	    sectionAddressMap.put("edx", "ds");
	    sectionAddressMap.put("ebx", "ds");
		
	    sectionAddressMap.put("esp", "ss");
	    sectionAddressMap.put("ebp", "ss");
		
	    sectionAddressMap.put("esi", "ds");
	    sectionAddressMap.put("edi", "ds");
  }
   
  private static void init(){
		typeMappingMap=new HashMap<String,String>();
		typeMappingMap.put("eax", "reg");
		typeMappingMap.put("ecx", "reg");
		typeMappingMap.put("edx", "reg");
		typeMappingMap.put("ebx", "reg");
		typeMappingMap.put("esp", "reg");
		typeMappingMap.put("ebp", "reg");
		typeMappingMap.put("esi", "reg");
		typeMappingMap.put("edi", "reg");
		
		typeMappingMap.put("ax",  "reg");
		typeMappingMap.put("dx",  "reg");
		typeMappingMap.put("cx",  "reg");
		typeMappingMap.put("bx",  "reg");
		
		typeMappingMap.put("eax4", "regn");
		typeMappingMap.put("ecx4", "regn");
		typeMappingMap.put("edx4", "regn");
		typeMappingMap.put("ebx4", "regn");
		typeMappingMap.put("esp4", "regn");
		typeMappingMap.put("ebp4", "regn");
		typeMappingMap.put("esi4", "regn");
		typeMappingMap.put("edi4", "regn");
		//al等寄存器
		typeMappingMap.put("al",  "regl");
		typeMappingMap.put("dl",  "regl");
		typeMappingMap.put("cl",  "regl");
		typeMappingMap.put("bl",  "regl");
		
        typeMappingMap.put("byteptr",  "byteptr");
		typeMappingMap.put("wordptr",  "wordptr");
		typeMappingMap.put("dwordptr", "dwordptr");
		
		typeMappingMap.put("disp8",    "disp8");
		typeMappingMap.put("disp32",   "disp32");
		
		typeMappingMap.put("Iv",   "Iv");
		typeMappingMap.put("Ib",   "Ib");
		typeMappingMap.put("Iz",   "Iz");
		typeMappingMap.put("Iw",   "Iw");
		typeMappingMap.put("nop",   "nop");
		typeMappingMap.put("oneOpand",   "oneOpand");
		
	}
	
	public static String getValue(String key){
		String value=typeMappingMap.get(key);
		if(value==null){
			value=getValueLst(key);
			if(value==null)value=key;
		}
		 return value;
	}	
	
	private static String getValueLst(String key){
		StringBuffer valueBuf=new StringBuffer();
		Set<String> keySet=typeMappingMap.keySet();
		for (String keyReg : keySet) {
			if(key.contains(keyReg)){
				valueBuf.append(typeMappingMap.get(keyReg));
				key=key.replace(keyReg, "");
			}
		}
		return valueBuf.toString();
	}
	
	
	
	private void test_getValueLst(){
		String key="ecxeax";
		StringBuffer retBuf=new StringBuffer();
		getValueLst(key);
		System.out.println(retBuf.toString());
	}
	
	public static String getSectionAddressValue(String key){
		  return sectionAddressMap.get(key);
	}
	
	public static void main(String[] args) {
		CreateOpcodeStrBean c=new CreateOpcodeStrBean();
		c.test_getValueLst();
	}
}
