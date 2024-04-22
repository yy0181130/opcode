package util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import structs.DWord;

/**
 * 与字节有关的方法
 * @author ChenTao
 *
 */
public class ByteUtil {
	
	public final static char[] digits = {'0', '1'};
	
	public static boolean firstBitIsZero(DWord unionAddress){
		Integer[] orgByte=unionAddress.getOrgByte();
		Integer orgFirstValue=null;
		for (int i = 0; i < orgByte.length; i++) {
			if(orgByte[i]!=0){
				orgFirstValue=orgByte[i];
				break;
			}
		}
		if(orgFirstValue==null)return true;
		String value=toBinaryString(orgFirstValue.byteValue());
		char firstValue=value.charAt(0);
		int firstValueInt=Character.getNumericValue(firstValue);
		if(firstValueInt!=0)return false;
		return true;
	}
	
	/**
	 * 将整数转换为字节数组的方法
	 */
	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];
		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}
	
	/**
	 * 将整数转换为4位二进制串的方法
	 * @param value		整数值
	 * @return				4位二进制字符串
	 */
	public static String intTo4BitsBinaryString(int value) {
		byte b = (byte) (value & 0xff);
		
		char[] buff = new char[4];
		int charPos = 4;  
		do {
			buff[--charPos] = digits[b & 1];  
			b >>>= 1;
		} while (charPos > 0);
	    
	    return new String(buff);
	}
	
	/**
	 * 将整数转换为6位二进制串的方法
	 * @param value		整数值
	 * @return 				6位二进制字符串
	 */
	public static String intTo6BitsBinaryString(int value) {
		byte b = (byte) (value & 0xff);

		char[] buff = new char[6];
		int charPos = 6;
		do {
			buff[--charPos] = digits[b & 1];
			b >>>= 1;
		} while (charPos > 0);

		return new String(buff);
	}
	
	/**
	 * 把字节转换成二进制(0, 1)字符串
	 * @param b
	 * @return
	 */
	public static String toBinaryString(byte b) {
		char[] buff = new char[8];
		int charPos = 8;  
		do {
			buff[--charPos] = digits[b & 1];  
			b >>>= 1;
		} while (charPos > 0);
	    
	    return new String(buff);
	}
	
	/**
	 * 把二进制(0, 1)字符串转换成字节
	 * @param byteStr
	 * @return
	 */
	public static byte decodeBinaryString(String byteStr) {
		int re = 0;
	    if (byteStr.length() != 8) {
	    	System.err.println("Error binary string: " + byteStr);
	    	System.exit(0);
	    }
	    else {
	    	re = Integer.parseInt(byteStr, 2);
	    }
	    return (byte) re;  
	}
	
	/**
	 * 把二进制(0, 1)字符串转换成int型
	 * @param byteStr
	 * @return
	 */
	public static int decodeBinaryStringToInteger(String byteStr) {
		return Integer.parseInt(byteStr, 2);
	}
	
	/**
	 * 将字节List转成字节数组
	 * @param bytes
	 * @return
	 */
	public static byte[] convert(List<Byte> bytes) {
		byte[] byteArray = new byte[bytes.size()];
		for (int i  = 0; i < bytes.size(); i++) {
			byteArray[i] = bytes.get(i);
		}
		return byteArray;
	}
	
	
	
	
	
	/**
	 * 将时间字符串转换成用于OctetString的字节数组
	 * @param timeString	时间字符串，为"HH:mm:ss"的格式
	 * @return					字节数组
	 */
	public static byte[] convertTime(String timeString) {
		// 获得数据数组
		String[] da = timeString.split(":");
		// 分别获得时分秒
		int hour = Integer.parseInt(da[0]);
		int minute = Integer.parseInt(da[1]);
		int second = Integer.parseInt(da[2]);
		
		byte hourByte = (byte) ((byte) hour & 0xff);
		byte minuteByte = (byte) ((byte) minute & 0xff);
		byte secondByte = (byte) ((byte) second & 0xff);
		return new byte[] {hourByte, minuteByte, secondByte, (byte) 0xff};	// 0xff表示毫秒
	}
	
	/**
	 * 将ASCII字符串转换成字节数组
	 * @param ascString	ASCII字符串
	 * @return					字节数组
	 */
	public static byte[] convertASCII(String ascString) {
		int length = ascString.length();
		byte[] ba = new byte[length];
		for (int i = 0; i < length; i++)
			ba[i] = (byte) ascString.charAt(i);
		return ba;
	}
	
	/**
	 * 将整型List转换成字节List
	 * @param ints
	 * @return
	 */
	public static List<Byte> convertIntegersToBytes(List<Integer> ints) {
		List<Byte> bytes = new ArrayList<Byte>();
		for (Integer integer : ints) {
			bytes.add((byte) integer.intValue());
		}
		return bytes;
	}
	
	/**
	 * 将字节List转换成0/1字符串
	 * @param lst
	 * @return
	 */
	public static String convertBytesToString(List<Byte> lst) {
		String rs = "";
		for (Byte byte1 : lst) {
			rs += toBinaryString(byte1);
		}
		return rs;
	}
	
	public static byte[] getByteOfLegth(byte[] allFrame,int size){
		byte[] frame=new byte[size];
		for (int i = 0; i < size; i++) {
			frame[i]=allFrame[i];
		}
		return frame;
	}
	
	/**
	 * 将ascii转为string
	 * @param value
	 * @return
	 */
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split(",");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return sbu.toString();  
	}  
	
	public static int getByteArrayToInt(byte[] context){
	 int intValue=0;
	 for(int i=0;i<context.length;i++)
	    intValue +=(context[i] & 0xff)<<((context.length-i-1));
	 return intValue;
    } 
	
	public static byte[] intToByteArray(int i)  { 
		ByteArrayOutputStream buf = new ByteArrayOutputStream(); 
		DataOutputStream dos= new DataOutputStream(buf); 
		try {
			dos.writeInt(i);
			byte[] b = buf.toByteArray(); 
		    return b; 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				dos.close();
				buf.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	
		}
		return new byte[]{};
	} 
	
	  public static byte[] fromStrWithBlank(String s){
		    s = s.replaceAll("0x", "");  
		    s = s.replaceAll(",", " "); 
			s = s.replaceAll("\\s*", "");  
			List<Byte> lstByte=new ArrayList<Byte>();
			for (int i = 0; i < s.length(); i=i+2) {
				String sSingal=s.substring(i,i+2);
				Integer sInt=Integer.parseInt(sSingal, 16);
				byte m=sInt.byteValue();
				lstByte.add(m);
			}
			byte []retBytes=convert(lstByte);
			return retBytes;
	  }
	
	
	
	private  static void  test_getByteArrayToInt(){
		byte code[]=new byte[]{0x00,(byte)0xf0};
		int value=getByteArrayToInt(code);
		System.out.println(value);
	}
	
	private void test_toBinaryString(){
		int i = 256+ 256;
		String rawData = String.valueOf(i);
		byte[] ba = ByteUtil.int2byte(Integer.parseInt(rawData));
		String bitValue = ByteUtil.toBinaryString(ba[1]) + ByteUtil.toBinaryString(ba[0]);
		System.out.println(bitValue);
	}
	
	
	public static void main(String[] args) throws IOException {
//		test_getByteArrayToInt();
		
		System.out.println(intTo4BitsBinaryString(3));
	}
}
