package structs;

import java.util.ArrayList;

import util.AsciiUtil;

public class AsciiBytes {
	private int[] value;
	private String valueStr;
	private int size;
	
    public int getSize() {
		return size;
	}

	public String getValueStr() {
		return valueStr;
	}

	public AsciiBytes(ArrayList<Integer>  codeLst,int index,int bytesSize) {
		size=bytesSize;
    	value=new int[bytesSize];
        for (int i = 0; i < bytesSize; i++) {
        	value[i]=codeLst.get(index+i);
		}
        valueStr= AsciiUtil.ascii2String(value);
    }
}
