package cpu.util;

import java.util.HashMap;
import java.util.Map;

import cpu.util.bean.ModRMUBean;

public class ModRmUtil {
   private static Map<String,ModRMUBean> modrmEffAMap;
   static {
	   modrmEffAMap=new HashMap<String,ModRMUBean>();
	   ModRMUBean mBean=new ModRMUBean("eax","ax","al","mm0","xmm0");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("ecx","cx","cl","mm","xmm1");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("edx","dx","dl","mm2","xmm2");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("ebx","bx","bl","mm3","xmm3");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("esp","sp","ah","mm4","xmm4");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("ebp","bp","ch","mm5","xmm5");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("esi","si","dh","mm6","xmm6");
	   modrmEffAMap.put(mBean.getR32(), mBean);
	   mBean=new ModRMUBean("edi","di","bh","mm7","xmm7");
	   modrmEffAMap.put(mBean.getR32(), mBean);
   } 
 
   
   public static ModRMUBean getEffective(String r32){
	   return modrmEffAMap.get(r32);
   }
}
