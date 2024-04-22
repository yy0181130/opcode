package structs;

public class Address {
	private Byte[] addressByteCode;
	
	
   public Address(String address) {
	   int index=0;
	   addressByteCode=new Byte[address.length()/2];
	   for (int i = 0; i < address.length(); ) {
		 String codeStr=address.substring(i,i+2);
		 Integer codeValue=Integer.parseInt(codeStr,16);
		 addressByteCode[index++]=codeValue.byteValue();
		 i=i+2;
	  }
   }
   
   public String addIntValue(int addValue){
	 Byte value= addressByteCode[addressByteCode.length-1];
	 Integer value2=value&0xff;
	 Integer value3=value2+addValue;
	 addressByteCode[addressByteCode.length-1]=value3.byteValue();
	 return toString();
   }
   
   public String toString(){
	   StringBuffer retValue=new StringBuffer();
	   for (int i = 0; i < addressByteCode.length; i++) {
		 String hex = Integer.toHexString(addressByteCode[i] & 0xFF);
		 if(hex.length()==1) retValue.append("0");
		  retValue.append(hex);
	   }
	   return retValue.toString();
   }
   
   
   public static void main(String[] args) {
	String address="004020b5";
	Address a=new Address(address);
	String aa=a.addIntValue(10);
	System.out.println(aa);
   }
}
