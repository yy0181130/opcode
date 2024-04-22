package cpu.aid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import util.GetACT;
import util.Loader;
import cpu.CpuEngine;
import cpu.bean.FpuMapBean;
import cpu.bean.FpuMapBeanLst;
import cpu.bean.FpuMapContextBean;
import cpu.bean.ModRMBean;
import cpu.bean.OneByteOpcodeBean;
import cpu.operand.bean.OperandBean;
import cpu.operand.bean.OperandContextBean;
import cpu.operand.choose.OperandType;
import cpu.parent.CpuObject;
import cpu.register.Register;

@Service
public class FpuParser extends CpuObject{
    private  FpuMapBeanLst fpuMapLst;
    private Map<String,FpuMapBean> fpuMap;
    
    public FpuParser() {
        fpuMapLst=(FpuMapBeanLst)Loader.getObjFromXmlFile(CpuEngine.class,"fpuOpcode.xml",FpuMapBeanLst.class);
        fpuMap=new HashMap<String,FpuMapBean>();
        List<FpuMapBean> fpuLst=fpuMapLst.getFpuMapBeanLst();
        for (FpuMapBean fpuMapBean : fpuLst) {
            fpuMap.put(fpuMapBean.getOpcode(), fpuMapBean);
        }
    }
    
    /**
     * 如果写了handler,则转到自定义的地方去处理
     */
    @Override
    public void decode(List<Integer> frameLst, CpuObject parameterObj) {
       ModRM modRm=(ModRM)GetACT.getBean("modRM");
        modRm.setModRm(frameLst, parameterObj);
        ModRMBean modRMBean=(ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        Integer opcodeInt=modRMBean.getModrmValue();
        if(opcodeInt<0xc0){
            doRegHandler(frameLst,parameterObj);
        }else{
            doOpcodeHandler(frameLst,parameterObj);
        }
        
        ShowAssembler showAssembler=(ShowAssembler)GetACT.getBean("showAssembler");
        showAssembler.show(parameterObj);
      }    
    /**
     * 小数的处理
     * @param frameLst
     * @param parameterObj
     */
    private void doRegHandler(List<Integer> frameLst, CpuObject parameterObj){
        OneByteOpcodeBean oneByteOpcodeBean = (OneByteOpcodeBean) parameterObj.getParameter(OneByteOpcodeBean.class.getSimpleName());
        String opcode =oneByteOpcodeBean.getOpcode();
        ModRM modRm=(ModRM)GetACT.getBean("modRM");
        ModRMBean modRMBean=(ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        
        FpuMapBean fpumapBean=fpuMap.get(opcode);
        String effectiveAddress=modRm.getEffectiveAddress(modRMBean);
        ModRMBean modRBean= (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        String  reg=modRBean.getReg();
        FpuMapContextBean fpuMapContextBean=fpumapBean.getContextByReg(reg);
        String opcodeName=fpuMapContextBean.getOpcodeName();
        
        //设置显示的opcode
        OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
        operandBean.setOpcode(opcodeName);
        
        modRm.decode_getOperand(frameLst,parameterObj,effectiveAddress);
        OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
        
        if(fpuMapContextBean.getP1().equals("double-real"))
           operandIo.setOperandSize(parameterObj, 64);
    }
    
    
    private void doOpcodeHandler(List<Integer> frameLst, CpuObject parameterObj){
        OneByteOpcodeBean oneByteOpcodeBean = (OneByteOpcodeBean) parameterObj.getParameter(OneByteOpcodeBean.class.getSimpleName());
        String opcode =oneByteOpcodeBean.getOpcode();
        
        FpuMapBean fpumapBean=fpuMap.get(opcode);
        ModRMBean modRBean= (ModRMBean) parameterObj.getParameter(ModRMBean.class.getSimpleName());
        Integer modrmValue=modRBean.getModrmValue();
        FpuMapContextBean fpuMapContextBean=fpumapBean.getContextByModRm(modrmValue);
        String opcodeName=fpuMapContextBean.getOpcodeName();
        
         //设置显示的opcode
         OperandBean operandBean = (OperandBean) parameterObj.getParameter(OperandBean.class.getSimpleName());
         operandBean.setOpcode(opcodeName);
         
         OperandIo operandIo=(OperandIo)GetACT.getBean("operandIo");
         if(fpuMapContextBean.getP1().equals("ax")){
             operandIo.setOperandType(parameterObj, OperandType.RegisterType);
             OperandContextBean contextBean=new OperandContextBean();
             contextBean.setRegister(Register.getRegisterByName(fpuMapContextBean.getP1()));
             operandIo.setOperanadContext(parameterObj,contextBean);
         }
     }
    
    
    
    
    private void test_1(){
      FpuMapBean f=fpuMap.get("dd");
      System.out.println(f.getOpcode());
      
    }
    
    
    
    public static void main(String[] args) {
        FpuParser f=new FpuParser();
        f.test_1();
    }
}
