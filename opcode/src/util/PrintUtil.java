package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintUtil {
	private static Logger logger = LogManager.getLogger(PrintUtil.class);
	private static boolean isInDebug = true;

	static {
		String isHexStr = "true";
		if (isHexStr.equals("true"))
			isInDebug = true;
		else
			isInDebug = false;
	}

	@SuppressWarnings("rawtypes")
	public String print(Map valueMap) {
		Set keySet = valueMap.keySet();
		Iterator ite = keySet.iterator();
		StringBuffer strBuf = new StringBuffer();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			String value = (String) valueMap.get(key);
			strBuf.append(key).append(":").append(value).append(";");
		}
		System.out.println(strBuf.toString());
		return strBuf.toString();
	}

	@SuppressWarnings("rawtypes")
	public String println(Map valueMap) {
		Set keySet = valueMap.keySet();
		Iterator ite = keySet.iterator();
		StringBuffer strBuf = new StringBuffer();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			String value = (String) valueMap.get(key);
			strBuf.append(key).append(":").append(value).append("\n");
		}
		System.out.println(strBuf.toString());
		return strBuf.toString();
	}

	public static void printHexArray(List<Byte> byteLst) {
		byte[] byteArray = new byte[byteLst.size()];
		for (int i = 0; i < byteLst.size(); i++) {
			byteArray[i] = byteLst.get(i);
		}
		printHexArray(byteArray);
	}

	public static void printHexArrayInt(List<Integer> byteLst) {
		byte[] byteArray = new byte[byteLst.size()];
		for (int i = 0; i < byteLst.size(); i++) {
			byteArray[i] = byteLst.get(i).byteValue();
		}
		printHexArray(byteArray);
	}
	
	public static void printHexArray(Integer byteFrame) {
	String hexCode=HexCodeUtil.getHexCodeByInt(byteFrame);
	logger.info(hexCode);
	}

	public static void printHexArray(ArrayList<Integer> frameLst) {
		if (frameLst.size() == 0)
			return;
		byte[] byteArray = new byte[frameLst.size()];
		for (int i = 0; i < frameLst.size(); i++) {
			byteArray[i] = frameLst.get(i).byteValue();
		}
		printHexArray(byteArray);
	}

	public static void printHexOfLstArray(String classMedName,List<Integer> frameLst) {
		if (frameLst.size() == 0)
			return;
		byte[] byteArray = new byte[frameLst.size()];
		for (int i = 0; i < frameLst.size(); i++) {
			byteArray[i] = frameLst.get(i).byteValue();
		}
		printHexArray(classMedName,byteArray);
	}

	
	
	public static void printHexArray(LinkedList<Integer> frameLst) {
		if (frameLst.size() == 0)
			return;
		byte[] byteArray = new byte[frameLst.size()];
		for (int i = 0; i < frameLst.size(); i++) {
			byteArray[i] = frameLst.get(i).byteValue();
		}
		printHexArray(byteArray);
	}

	public static void printHexArray(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());
			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);
		}
		if (isInDebug)
			logger.info(toHexStrArray(sb.toString()));
		else
			logger.info(sb.toString());
	}

	public static void printHexArrayWithByte(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());

			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);
		}
		if (isInDebug)
			logger.info(toHexStrArrayWithByte(sb.toString()));
		else
			logger.info(sb.toString());
	}

	public static void printHexArray(byte[] byteArray, int size, String title) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());
			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);
		}
		// logger.info(title+":"+sb.toString());
		if (isInDebug)
			logger.info(title + ":" + toHexStrArray(sb.toString()));
		else
			logger.info(title + ":" + sb.toString());
	}

	public static String toHexString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());
			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);

		}
		if (isInDebug)
			return toHexStrArray(sb.toString());
		else
			return sb.toString();
	}

	public static String toHexString(byte[] byteArray, int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());
			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);

		}
		if (isInDebug)
			return toHexStrArray(sb.toString());
		else
			return sb.toString();

	}

	public static void printHexArray(String title, byte[] byteArray) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			String hexValue = Integer.toHexString(byteArray[i]);
			if (hexValue.length() == 1)
				hexValue = "0" + hexValue;
			else if (hexValue.length() > 2)
				hexValue = hexValue.substring(hexValue.length() - 2,
						hexValue.length());
			if (i == 0)
				sb.append(hexValue);
			else
				sb.append("," + hexValue);
		}

		if (isInDebug)
			logger.info(title + ":" + toHexStrArray(sb.toString()));
		else
			logger.info(title + ":" + sb.toString());
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

	public static void printHexArray(Vector<Byte> v) {
		byte[] revBytes = new byte[v.size()];
		int i = 0;
		for (Byte c : v)
			revBytes[i++] = c.byteValue();
		printHexArray(revBytes);
	}

	private static void test_printHexArrayWithByte() {
		byte frameArray[] = new byte[] { (byte) 0xc4, 0x01, (byte) 0xc1, 0x00,
				0x02, 0x02, 0x0f, (byte) 0xff, 0x16, 0x1b, (byte) 0xa7, 0x1b };
		printHexArrayWithByte(frameArray);
	}

	public static void main(String[] args) {
		test_printHexArrayWithByte();
	}

}
