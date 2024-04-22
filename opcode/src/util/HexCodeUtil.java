package util;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class HexCodeUtil {
	/*
	 * 16进制数字字符集
	 */
	private static String hexString = "0123456789ABCDEF";
	
	public static String getHexCodeByInt(Integer codeInt) {
		String hexValue = Integer.toHexString(codeInt);
		if (hexValue.length() == 1)
			hexValue = "0" + hexValue;
		else if (hexValue.length() > 2)
			hexValue = hexValue.substring(hexValue.length() - 2,
					hexValue.length());

		hexValue = toHexStrArray(hexValue);
		return hexValue;
	}
	private static String toHexStrArray(String str) {
		String[] strArray = str.split(",");
		for (int i = 0; i < strArray.length; i++) {
			String value = strArray[i];
			value = "0x" + value;
			strArray[i] = value;
		}
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			if (i == 0)
				strBuf.append(strArray[i]);
			else
				strBuf.append(",").append(strArray[i]);
		}
		return strBuf.toString();
	}
	
	public static String byteValueToHexStr(Byte value){
		StringBuffer retStrBuf=new StringBuffer();
		Integer valueInt=0;
		if(value<0)
			retStrBuf.append("-");
          valueInt=Math.abs(value);
		  String hexValue=Integer.toHexString(valueInt);
		  retStrBuf.append(hexValue);
		  return retStrBuf.toString();
	}

	private static String toHexStrArrayWithByte(String str) {
		String[] strArray = str.split(",");
		for (int i = 0; i < strArray.length; i++) {
			String value = strArray[i];
			value = "0x" + value;
			int codeI = Integer.parseInt(strArray[i], 16);
			if (codeI > 127)
				value = "(byte)" + value;
			strArray[i] = value;
		}
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			if (i == 0)
				strBuf.append(strArray[i]);
			else
				strBuf.append(",").append(strArray[i]);
		}
		return strBuf.toString();
	}

	// 转化字符串为十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 转化十六进制编码为字符串
	public static String toStringHex1(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	// 转化十六进制编码为字符串
	public static String toStringHex2(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}



	

	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}

	/*
	 * 将16进制数字解码成字符串,适用于所有字符（包括中文）
	 */
	public static String decode(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
					.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}



	/**
	 * 将指定byte数组以16进制的形式打印到控制台
	 * 
	 * @param hint
	 *            String
	 * @param b
	 *            byte[]
	 * @return void
	 */
	public static void printHexString(String hint, byte[] b) {
		System.out.print(hint);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");
	}


	
	/**
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}
	
	public static String bytesLst2HexString(List<Integer> frameLst){
	    byte b[]=new byte[frameLst.size()];
	    for (int i = 0; i < frameLst.size(); i++) {
	        Integer value=frameLst.get(i);
	        b[i]=value.byteValue();
	      }
	   return bytes2HexString(b);
	}
	

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] hexString2Bytes(String src) {
		byte[] ret = new byte[8];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}
	
	/**
	 * 将大量Hex转为10进制的方法 当HEX的值超过Int的范围时不适用
	 * @param hexValue
	 * @return
	 */
	public static int hexStringToNumberSmall(String hexValue){
		int powCount=0;
		int sum=0;
		for (int i = hexValue.length()-1; i >-1 ; i--) {
		   String temp=""+hexValue.charAt(i);
		   int signalNum=Integer.parseInt(temp,16);
		   double signalNumHex=(double)signalNum*Math.pow(16,powCount++);
		   sum+=signalNumHex;
		}
		return sum;
	}
	
	public static double hexStringToNumberBig(String hexValue){
		int powCount=0;
		double sum=0;
		for (int i = hexValue.length()-1; i >-1 ; i--) {
		   String temp=""+hexValue.charAt(i);
		   int signalNum=Integer.parseInt(temp,16);
		   double signalNumHex=(double)signalNum*Math.pow(16,powCount++);
		   sum+=signalNumHex;
		}
		return sum;
	}	
	
	
	public static int hexStringToNumberEasy(String hexValue){
		int res=Integer.parseInt(hexValue,16);
		return res;
	}
	
	
	
	private void test_hex(){
		String a="0f0";//2684399907
		//Integer.toHexString(i);
	    int mm=hexStringToNumberEasy(a);
	    System.out.println(mm);
	}
	 
	
	public static void main(String[] args) {
		HexCodeUtil h=new HexCodeUtil();
		h.test_hex();
	}
}
