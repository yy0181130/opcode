package structs;

import java.util.List;

import structs.operator.IOperatorNum;
import util.HexCodeUtil;

/**
 * 带符号数
 * @Description:TODO
 * @author youy
 * @time:2014-3-19
 */
public class SByte implements IOperatorNum{
    private static final int size=1;//占1个字节 
    private Integer code;
	
	public SByte(List<Integer>  frameLst,int index) {
	    byte codeValue=frameLst.get(index).byteValue();
	    this.code=new Integer(codeValue);
	}
	
	public SByte(Integer code) {
	        this.code=new Integer(code.byteValue());
	}
	
	
	
	@Override
	public String toHexString() {
		Byte secondOperantByte = code.byteValue();
		String sHexValue = HexCodeUtil.byteValueToHexStr(secondOperantByte);
		return sHexValue;
	}


	
	public static int getSize() {
		return size;
	}


	@Override
	public Integer toDecValue() {
		return code;
	}


	@Override
	public Object toValue() {
		return null;
	}
	
	public static void main(String[] args) {
	    SByte s=new SByte(0xe0);
	    System.out.println(s.toDecValue());
    }
     
}
