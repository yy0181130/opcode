package test;

import structs.DWord;
import cpu.register.Register;

public class EnumTest {
   public static void main(String[] args) {
	   DWord df=new DWord("12345678");
	   DWord df2=new DWord("12345679");
	   Register.eax.setValue(df);
	   Register.ebx.setValue(df2);
	   System.out.println(Register.eax.getValue().toHexString());
	   System.out.println(Register.ebx.getValue().toHexString());
	   
  } 
}
