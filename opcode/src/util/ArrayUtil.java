package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author youy
 * 与数组相关的方法
 *
 */
public class ArrayUtil {
	
	
	public static boolean isArrayZero(List<Integer> codeLst,int size){
		for (int i = 0; i < size; i++) {
			if(codeLst.get(i)!=0)return false;
		}
		return true;
	}
	
	/**
	 * 将若干个数组合并为一个数组
	 * @param obj
	 * @return
	 */
    public static byte[] mergeArrays(Object obj[]){
    	byte[] new_a3 = new byte[getArraysLength(obj)];
        int index = 0;
        for(Object objs:obj){
           for(byte a:(byte[])objs){
               new_a3[index++] = a;
             }
        }
        return new_a3;
     }
    
    /**
     * 将字节数组添加到集合中
     * @param v
     * @param bArray
     */
    public static void addToVector(Vector<Byte> v,byte[] bArray){
    	for (int i = 0; i < bArray.length; i++) {
			v.add(bArray[i]);
		}
    }
	
    /**
     * 求需要合并数组的总长度
     * @param obj
     * @return 总长度,-1:obj为无效数组
     */
    public static int getArraysLength(Object[] obj) {
        int length = 0;
        if (obj != null && obj.length > 0) {
           for (Object objs : obj) {
             length = length+((byte[]) objs).length;
           }
        return length;
        }
     return -1;
    }
    
    /**
     * 比较两个字节数组中内容是否相同
     * @param byteArray1
     * @param byteArray2
     * @return
     */
    public static boolean compareByteArray(byte[] byteArray1, byte[] byteArray2) {
    	if (byteArray1.length != byteArray2.length) {
    		return false;
    	}
    	else {
    		for (int i = 0; i < byteArray1.length; i++) {
    			if (byteArray1[i] != byteArray2[i]) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    /**
     * 合并两个字节数组
     * @param arrayFirst
     * @param arraySecond
     * @return
     */
    public static byte[] mergeByteArray(byte[] arrayFirst, byte[] arraySecond) {
    	if (arrayFirst == null)
    		return arraySecond;
    	byte[] array = new byte[arrayFirst.length + arraySecond.length];
    	for (int i = 0; i < array.length; i++) {
    		if (i < arrayFirst.length) {
    			array[i] = arrayFirst[i];
    		}
    		else {
    			array[i] = arraySecond[i - arrayFirst.length];
    		}
    	}
    	return array;
    }
    
    /**
     * 将一个list转为byte数组
     * @param completeTcpPackage
     * @return
     */
    public static byte[] lstTobyteArray( List<Byte> completeTcpPackage){
 	   byte[] retArray=new byte[completeTcpPackage.size()];
 	   for (int i = 0; i < completeTcpPackage.size(); i++) {
 		  retArray[i]=completeTcpPackage.get(i);
 	     }
 	   return retArray;
 	}
    
    public static List<Byte> byteArrayToLst(byte[] byteArray){
    	List<Byte> byteLst=new ArrayList<Byte>();
     for (int i = 0; i < byteArray.length; i++) {
    	 byteLst.add(byteArray[i]);
	     }
        return byteLst;
     }
    
    /**
     * 将byte数组添加到链表当中
     * @param frameArray
     * @param frameLst
     */
	public static void addByteToLinkedLst(byte frameArray[], List<Integer> frameLst) {
		for (int i = 0; i < frameArray.length; i++) {
			int result = frameArray[i] & 0xff;
			frameLst.add(result);
		}
	}
	
    public static byte[] intLstTobyteArray( List<Integer> codeLst){
  	   byte[] retArray=new byte[codeLst.size()];
  	   for (int i = 0; i < codeLst.size(); i++) {
  		  retArray[i]=codeLst.get(i).byteValue();
  	     }
  	   return retArray;
  	}
    
    /**
     * 划分字节数组的方法
     * @param rawData		原始数据
     * @param size			划分的大小
     * @return					划分后的数组的List
     */
    public static List<byte[]> splitByteArray(byte[] rawData, int size) {
    	List<byte[]> byteArrayList = new ArrayList<byte[]>();
    	int listSize = rawData.length / size + ((rawData.length % size == 0) ? 0 : 1);
    	for (int i = 0; i < listSize; i++) {
    		byte[] temp = new byte[(i == listSize - 1) ? (rawData.length - size * i) : size];
    		for (int j = 0; j < temp.length; j++)
    			temp[j] = rawData[j + size * i];
    		byteArrayList.add(temp);
    	}
    	return byteArrayList;
    }
    
    public static byte[] intArray2ByteArray(int b[]){
    	byte[] rcode=new byte[b.length];
    	for (int i = 0; i < b.length; i++) {
    		rcode[i]=(byte)b[i];
		}
    	return rcode;
    }
    
    public static void main(String[] args) {
		byte[] test = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
		List<byte[]> list = ArrayUtil.splitByteArray(test, 3);
		for (byte[] bs : list) {
			PrintUtil.printHexArray(bs);
		}
	}
}
