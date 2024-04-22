package test.ojb;

public class Test2 extends Test1{
  public void test2(){
	  a=3;
  }
  
  public static void main(String[] args) {
	  Test1 t=new Test1();
	  t.setA();
	  Test2  t2=(Test2)t;
	  t2.test2();
	  System.out.println(t2.a);
}
  
}
