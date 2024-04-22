package cpu.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FpuMapBean {
    @XmlAttribute(required = true)
    protected String opcode;
    
    @XmlElement(name = "context")
    protected List<FpuMapContextBean> contextLst=new ArrayList<FpuMapContextBean>();
    
    
    public FpuMapBean() {
    }
    
    public FpuMapBean(String opcode) {
        this.opcode = opcode;
    }


    public String getOpcode() {
        return opcode;
    }

    
    public void addContext(FpuMapContextBean fcontextBean){
        contextLst.add(fcontextBean);
    }
    
    public List<FpuMapContextBean> getContextLst() {
        return contextLst;
    }
    
    public FpuMapContextBean getContextByReg(String reg){
        for (FpuMapContextBean fpuMapContextBean : contextLst) {
            if(fpuMapContextBean.reg.equals(reg))return fpuMapContextBean;
        }
        return null;
    }
    
    public FpuMapContextBean getContextByModRm(Integer moderRm){
        for (FpuMapContextBean fpuMapContextBean : contextLst) {
           Integer moderRmInLst= Integer.parseInt(fpuMapContextBean.getModrm(),16);
           if(moderRmInLst.equals(moderRm))return fpuMapContextBean;
        }
        return null;
    }
    
}
