package test.ascii;

import util.AsciiUtil;
import util.ByteUtil;

public class Test {
   public static void main(String[] args) {
		byte[] value = ByteUtil.fromStrWithBlank("70 71 40 00 46 5A 41 00 FE 56 41 00 F8 56 41 00");
		String s=AsciiUtil.ascii2String(value);
		System.out.println(s);
}
}
