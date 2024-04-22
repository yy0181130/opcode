package cpu.operand.impl;

import structs.operator.IOperatorNum;
import cpu.bean.InstructParserBean;
import cpu.operand.Operand;
import cpu.operand.bean.OperandContextBean;
import cpu.parent.CpuObject;
import cpu.register.OperandSize;
import cpu.register.Register;
import cpu.register.SegementRegister;
/**
 * 指针类型
 */
public class Point extends Operand{
    public Point(CpuObject parameterObj) {
		super(parameterObj);
	}

	private Register sibBase;
    private Register sibIndex;
    private Integer  sibScale;
    private IOperatorNum displacement;
     
    public String getSibIndex(){
    	return sibIndex.getRegisterName();
    }
    
    public String getSibBase(){
        return sibBase.getRegisterName();
    }
    
    public String getDisplacement(){
        return displacement.toHexString();
    }
    
    /**
     * 判断是否符合索引表的特征
     * @return
     */
    public boolean issibBaseAndDisplacementNotNull(){
        if(sibBase!=null&&sibIndex==null&&sibScale==null&&displacement!=null)return true;
        return false;
    }
    
    public boolean isBINotNull(){
        if(sibBase!=null&&sibIndex!=null&&sibScale==null&&displacement==null)return true;
        return false;
    }
    
    public boolean isBISNotNull(){
        if(sibBase!=null&&sibIndex!=null&&sibScale!=null&&displacement==null)return true;
        return false;
    }
    
    public boolean isSibBaseNotNull(){
        if(sibBase!=null&&sibIndex==null&&sibScale==null&&displacement==null)return true;
        return false;
    }
    
    public boolean isDisplacementNotNull(){
        if(sibBase==null&&sibIndex==null&&sibScale==null&&displacement!=null)return true;
        return false;
    }
    
    /**
     * 是否符合分支表条件
     * @return
     */
    public boolean isJmpSwitableOfDbTable(){
        if(sibBase==null&&sibIndex!=null&&sibScale!=null&&displacement!=null)return true;
        return false;
    }
    
    /**
     * 将信息设置到具体的类中
     */
	@Override
	public void setOperandContextBean(OperandContextBean contextBean) {
		if(contextBean.getSibBase()!=null)sibBase=contextBean.getSibBase();
		if(contextBean.getSibIndex()!=null)sibIndex=contextBean.getSibIndex();
		if(contextBean.getSibScale()!=null)sibScale=contextBean.getSibScale();
		if(contextBean.getDisplacement()!=null)displacement=contextBean.getDisplacement();
	}
   
	
	@Override
	public String toString() {
	   return getAssembler();
	}
	
	private String getAssembler(){
		String code=getAssemblerCode();
		return code+"]";
	}
	
	private String getAssemblerCode(){
		StringBuffer valueBuf=new StringBuffer();
		valueBuf.append(getOperandPrefix().getValue()).append(" ");//前缀,dword 等
			valueBuf.append(getSegmentRegister()).append(":").append("[");
			if(sibBase!=null){
				valueBuf.append(sibBase.getRegisterName());
				if(sibIndex==null&&displacement==null)return valueBuf.toString();
				valueBuf.append("+");
			}
			if(sibIndex!=null){
				valueBuf.append(sibIndex.getRegisterName());
				if(sibScale!=null)valueBuf.append("*").append(sibScale);
				if(displacement==null)return valueBuf.toString();
				valueBuf.append("+");
			}
		    if(displacement!=null)valueBuf.append(displacement.toHexString());
		
		return valueBuf.toString();
	}
	
	
	/**
	 * 选择段寄存器的过程
	 * @return
	 */
	public SegementRegister getSegmentRegister(){
		 CpuObject parameterObj=getParameterObj();
		 InstructParserBean prefixBean=(InstructParserBean)parameterObj.getParameter(InstructParserBean.class.getSimpleName());
		 SegementRegister s= prefixBean.getSegementRegister();
		 if(s!=null){
			 return s;
		 }
		
		if(sibBase==null&&displacement!=null){
			return SegementRegister.ds;
		}
		if(sibBase==Register.esp){
			return SegementRegister.ss;
		} 
		else if(sibBase==Register.ebp){
			return SegementRegister.ss;
		} 
		return SegementRegister.ds;
	} 
	
	private OperandSize getOperandPrefix(){
		return OperandSize.getOperandSize(getOperandSize());
	}
	
	
}
