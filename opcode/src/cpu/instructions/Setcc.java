package cpu.instructions;

import java.util.List;

import org.springframework.stereotype.Component;

import util.GetACT;
import cpu.aid.ModRM;
import cpu.aid.OperandsParser;
import cpu.bean.ModRMBean;
import cpu.parent.CpuObject;
import cpu.util.ModRmUtil;
import cpu.util.bean.ModRMUBean;

@Component
public class Setcc extends CpuObject{
	  public void decode(List<Integer> frameLst, CpuObject parameterObj) {
		    OperandsParser operandsParser=(OperandsParser)GetACT.getBean("operandsParser");
		    operandsParser.decode("setcc", frameLst, parameterObj);
	   }
	

    public void setcc_ne_nz(List<Integer> frameLst,CpuObject parameterObj){
    	ModRM modRm = (ModRM) GetACT.getBean("modRM");
   		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
   		modRBean=modRm.setModRm(frameLst, parameterObj);
   		String effAddress = modRm.getEffectiveAddress(modRBean);
   		ModRMUBean modrmuBean=ModRmUtil.getEffective(effAddress);
   		String r8=modrmuBean.getR8();
   	    logger.info("setne "+r8);
   	    setInstructLength(parameterObj, 3);
     }
    
    public void setcc_nl_ge(List<Integer> frameLst,CpuObject parameterObj){
        ModRM modRm = (ModRM) GetACT.getBean("modRM");
        ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        modRBean=modRm.setModRm(frameLst, parameterObj);
        String effAddress = modRm.getEffectiveAddress(modRBean);
        ModRMUBean modrmuBean=ModRmUtil.getEffective(effAddress);
        String r8=modrmuBean.getR8();
        logger.info("setge "+r8);
        setInstructLength(parameterObj, 3);
     }
    
    public void setcc_l_nge(List<Integer> frameLst,CpuObject parameterObj){
        ModRM modRm = (ModRM) GetACT.getBean("modRM");
        ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        modRBean=modRm.setModRm(frameLst, parameterObj);
        String effAddress = modRm.getEffectiveAddress(modRBean);
        ModRMUBean modrmuBean=ModRmUtil.getEffective(effAddress);
        String r8=modrmuBean.getR8();
        logger.info("setl "+r8);
        setInstructLength(parameterObj, 3);
     }
    
    
    
    public void setcc_e_z(List<Integer> frameLst,CpuObject parameterObj){
    	ModRM modRm = (ModRM) GetACT.getBean("modRM");
   		ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
   		modRBean=modRm.setModRm(frameLst, parameterObj);
   		String effAddress = modRm.getEffectiveAddress(modRBean);
   		ModRMUBean modrmuBean=ModRmUtil.getEffective(effAddress);
   		String r8=modrmuBean.getR8();
   	    logger.info("sete "+r8);
   	 }
    
    public void setcc_s(List<Integer> frameLst,CpuObject parameterObj){
        ModRM modRm = (ModRM) GetACT.getBean("modRM");
        ModRMBean modRBean = (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        modRBean=modRm.setModRm(frameLst, parameterObj);
        String effAddress = modRm.getEffectiveAddress(modRBean);
        ModRMUBean modrmuBean=ModRmUtil.getEffective(effAddress);
        String r8=modrmuBean.getR8();
        logger.info("sets "+r8);
        setInstructLength(parameterObj, 3);
     }
}
