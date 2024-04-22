package cpu.util;

import structs.SByte;
import structs.UByte;
import structs.operator.IOperatorNum;

public class HexCalculationUtil {
	   public static String addressAddInt(IOperatorNum a1,IOperatorNum a2){
		   Integer d3=0;
		   Integer a1ValueOfInt=0;
		   Integer a2ValueOfInt=0;
		   
		   if(a1 instanceof SByte||a1 instanceof UByte){
			   Integer tempValue=a1.toDecValue();
			   Byte  a1ValueOfByte=tempValue.byteValue();
			   a1ValueOfInt=new Integer(a1ValueOfByte);
		   }else{
			   a1ValueOfInt=a1.toDecValue();
		   }
		   if(a2 instanceof SByte||a2 instanceof UByte){
			   Integer tempValue=a2.toDecValue();
			   Byte  a2ValueOfByte=tempValue.byteValue();
			   a2ValueOfInt=new Integer(a2ValueOfByte);
		   }else{
			   a2ValueOfInt=a2.toDecValue();
		   }
		   d3=a1ValueOfInt+a2ValueOfInt;
		   String s3=Integer.toHexString(d3);
		   return s3;
	   }
	   
	   public static String addressStract(String address1,int reduction){
		   Integer d1=Integer.parseInt(address1,16);
		   Integer d3=d1-reduction;
		   String s3=Integer.toHexString(d3);
		   return s3;
	   }
	   
}
