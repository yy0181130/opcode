package structs;

import java.util.List;

import structs.operator.IOperatorNum;
import util.HexCodeUtil;

public class DWord implements IOperatorNum{
    private static final int size=4;//占四个字节
    private Integer[] orgByte=new Integer[4];
    private String codeStr;
    private double codeNumber;
    
    public Integer[] getOrgByte(){
    	return orgByte;
    }
    
	public double getCodeNumber() {
		return codeNumber;
	}

	public String toHexString() {
		return codeStr;
	}

	public static int getSize() {
		return size;
	}
	
	/**
	 * 按照文件中存储的方式来读取
	 * @param codeLst
	 * @param index
	 */
    public DWord(List<Integer>  frameLst,int index) {
    	orgByte[0]=frameLst.get(index+3);
		orgByte[1]=frameLst.get(index+2);
		orgByte[2]=frameLst.get(index+1);
		orgByte[3]=frameLst.get(index);
		setCodeStr();
		codeNumber=HexCodeUtil.hexStringToNumberBig(codeStr);
   }
    
    public DWord(List<Integer>  frameLst,int index,boolean rev) {
        this(frameLst,index);
        for (int i = 0; i < size; i++) 
        	  frameLst.remove(0);
	 }
    
    public DWord() {
	
	}
    
    
    public DWord(String address) {
    	codeStr=address;
    	codeNumber=HexCodeUtil.hexStringToNumberBig(codeStr);
     }
  
  
    public void refreshValue(String value){
     	codeStr=value;
    }
    
    
    private void setCodeStr(){
    	for (int i = 0; i < orgByte.length; i++) {
    		String s=Integer.toHexString(orgByte[i]);
    		if(s.length()==1)s="0"+s;
    		if(i==0)codeStr=s;
    		else codeStr+=s;
    	}
    }

	@Override
	public Integer toDecValue() {
		return new Double(codeNumber).intValue();
	}

	@Override
	public Object toValue() {
		return null;
	}
    
	
	
}
