package memory.util;

import structs.Address;


public class MemoryAddressUtil {
	
	/**
	 * 将两个地址相加，必须位数要相同
	 * @param address1
	 * @param address2
	 * @return
	 */
   public static String addressAdd(String address1,String address2){
	   StringBuffer result=new StringBuffer();
	   int size=address1.length();
	   String roundedStr="0";
	   for (int i = size-1; i > -1; i--) {
		   int rounded=Integer.parseInt(roundedStr,16);
		   String t1=""+address1.charAt(i);
		   String t2=""+address2.charAt(i);
		   Integer signalNum1=Integer.parseInt(t1,16);
		   Integer signalNum2=Integer.parseInt(t2,16);
		   String singalRes=Integer.toHexString(signalNum1+signalNum2+rounded);
		   if(singalRes.length()>1){
			   roundedStr=""+singalRes.charAt(0);
			   result.insert(0, singalRes.charAt(1));
		   }else{
			   roundedStr="0";
			   result.insert(0, singalRes.charAt(0));
		    }
		 }
	 return result.toString();
	}
   
   public static String addressAdd(String address1,int codeSize){
	   StringBuffer result=new StringBuffer();
	   int size=address1.length();
	   String roundedStr=String.valueOf(codeSize);
	   for (int i = size-1; i > -1; i--) {
		   int rounded=Integer.parseInt(roundedStr);
	       String t1=""+address1.charAt(i);
	       Integer signalNum1=Integer.parseInt(t1,16);
	       String singalRes=Integer.toHexString(signalNum1+rounded);
		   if(singalRes.length()>1){
			   roundedStr=""+singalRes.charAt(0);
			   result.insert(0, singalRes.charAt(1));
		   }else{
			   roundedStr="0";
			   result.insert(0, singalRes.charAt(0));
		    }
		 }
	 return result.toString();
	}
   
   //返回4字节数;只是增加最后一个字节的数值
   public static String addressAddOnlLastByte(String address1,int codeSize){
	   Address address=new Address(address1);
	   String newAddress=address.addIntValue(codeSize);
       return newAddress;
	}
   
   public static Integer addressSubtract(String address1,String address2){
	   Integer d1=Integer.parseInt(address1,16);
	   Integer d2=Integer.parseInt(address2,16);
	   Integer d3=d1-d2;
	   //String s3=Integer.toHexString(d3);
	   return d3;
   }
   public static String addressSubtractRetHex(String address1,String address2){
	   Integer d3=addressSubtract(address1,address2);
	   String s3=Integer.toHexString(d3);
	   return s3;
   }
   
   
   public static String addressAddInt(String address1,String address2){
	   Integer d1=Integer.parseInt(address1,16);
	   Integer d2=Integer.parseInt(address2,16);
	   Integer d3=d1+d2;
	   String s3=Integer.toHexString(d3);
	   return s3;
   }
   
   public static String addressAddInt(String address1,Integer address2){
       Integer d1=Integer.parseInt(address1,16);
       Integer d3=d1+address2;
       String s3=Integer.toHexString(d3);
       return s3;
   }
   
   
   private void test_1(){
	   MemoryAddressUtil u=new MemoryAddressUtil();
	   String address1="00a00000";
	   String address2="00a02000";
	   String s=addressAddInt(address1,address2);
	   System.out.println(s);
   }
   
   private void test_2(){
	   MemoryAddressUtil u=new MemoryAddressUtil();
	   String address1="00402093";
	    int size=123;
	   String s=addressAddOnlLastByte(address1,size);
	   System.out.println(s);
   }
   
   private void test_3(){
	   String s1="00403004";
	   String s2="00402000";
	   Integer d1=Integer.parseInt(s1,16);
	   Integer d2=Integer.parseInt(s2,16);
	   Integer d3=d1-d2;
	   String s3=Integer.toHexString(d3);
	   System.out.println(s3);
	   
   }
   
   private void test_4(){
	   String s1="00400000";
	   String s2="00015ffe";
	   String s3= addressAdd(s1,s2);
	   System.out.println(s3);
   }
   
   
   public static void main(String[] args) {
	   MemoryAddressUtil m=new MemoryAddressUtil();
	   m.test_4();
   }
}
