/**
 * 
 */
package util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import constant.Path;

/**
 * @author youy
 *
 * 2012-8-1
 */
public class GetACT {
	
	private static ApplicationContext  act=null;
	
    public static  ApplicationContext getApplicationContext(){
    	if(act==null)
    	  act = new ClassPathXmlApplicationContext(new String[]
    	  {
    		Path.cpuInstruction
    	 });
    	return act;
     }
    public static void setAct(ApplicationContext  context){
    	act=context;
    }
    
    public static Object getBean(String beanName){
    	return getApplicationContext().getBean(beanName);
    }
    
}
