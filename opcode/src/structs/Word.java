package structs;

import java.util.List;

import structs.operator.IOperatorNum;

public class Word implements IOperatorNum{
	private static final int size = 2;// 占2个字节
    private Integer[] orgByte=new Integer[2];
    private String codeStr;
    private Integer codeValue;
    
	public String toHexString() {
		return codeStr;
	}


	public Integer getCodeValue() {
		return codeValue;
	}


	public static int getSize() {
		return size;
	}
 
	
	public Word(List<Integer>  codeLst,int index) {
		orgByte[0]=codeLst.get(index+1);
		orgByte[1]=codeLst.get(index);
		String s1=Integer.toHexString(orgByte[0]);
		if(s1.length()==1)s1="0"+s1;
		String s2=Integer.toHexString(orgByte[1]);
		if(s2.length()==1)s2="0"+s2;
		codeStr=s1+s2;
		codeValue=Integer.parseInt(codeStr,16);
	}
	
    public Word(List<Integer>  frameLst,int index,boolean rev) {
        this(frameLst,index);
        for (int i = 0; i < size; i++)
        	  frameLst.remove(0);
	  }
	
	
	
	public static void main(String[] args) {
		String x="02E0";
		Integer a=Integer.parseInt(x,16);
		System.out.println(a);
		Integer[] orgByte=new Integer[2];
		orgByte[0]=0x02;
		orgByte[1]=0xE0;
		Integer xx=0x00;
		String s=String.valueOf(Integer.toHexString(xx));
		System.out.println(s);
	}


	@Override
	public Integer toDecValue() {
		return codeValue;
	}


	@Override
	public Object toValue() {
		return null;
	}
	
	
}
