package structs;

import java.util.List;

import structs.operator.IOperatorNum;

/**
 * 不带符号数byte
 * 
 * @author :youy
 * @time:2014年5月17日
 */
public class UByte implements IOperatorNum{
    private static final int size=1;//占四个字节
    private int code;
	public  static int getSize() {
		return size;
	}
	
	public UByte(Integer value) {
		this.code=value;
	}
	
	public UByte(List<Integer>  frameLst,int index) {
		this.code=frameLst.get(index);
	}
	
	public int toIntValue(){
		return code;
	}
	
	public String toHexString(){
		return Integer.toHexString(code);
	}
	
	public String toByteString(){
	      Byte value=(byte)code;
	      return String.valueOf(value);
	}

	@Override
	public Integer toDecValue() {
		return code;
	}

	@Override
	public Object toValue() {
		return null;
	}
	
}
