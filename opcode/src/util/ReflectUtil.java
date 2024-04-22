/**
 * 
 */
package util;

/**
 * @author youy
 *
 * 2012-7-13
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cpu.parent.CpuObject;



public class ReflectUtil {
	/**
	 * 按照类名反射出它的一个对象
	 * 
	 * @param classname
	 *            包名+类名
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object getObjByClassName(String classname) {
		Object obj = null;
		if (classname != null) {
			try {
				Class a = Class.forName(classname);
				obj = a.newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 按照类名,参数值反射出它的一个对象
	 * 
	 * @param classname
	 *            包名+类名
	 * @param parameter
	 *            构造函数的参数值
	 * @return Object
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	public static Object getObjByClassNameAndParameter(String classname,
			Object[] parameter) {
		Object obj = null;
		if (classname != null) {
			try {
				Class a = Class.forName(classname);
				// 获取公有的构造函数,指定参数
				Constructor con = a
						.getConstructor(getParameterClass(parameter));
				obj = con.newInstance(parameter);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}



	/**
	 * 用类名反射调用它的某个方法(一般针对工具类或者service)(有参数)
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param parameter
	 *            参数数组
	 * @return Object
	 */

	public static Object invokeMethodHasParame(Object obj,String methodName, Class<?>[] parameterType,Object[] parameter,CpuObject parameterObj) {
		Class thisClazz=obj.getClass();
	    try {
	     Method method = thisClazz.getDeclaredMethod(methodName, parameterType); 
	     method.setAccessible(true); 
		return 	method.invoke(obj, parameter);
		} catch (Exception e) {
			e.printStackTrace();
			parameterObj.setFindError(true);
		    return null;
		}
     }
	
	

	/**
	 * 获取参数列表的class对象
	 * 
	 * @param parameter
	 *            参数值数组
	 * @return Class[]
	 */
	@SuppressWarnings("rawtypes")
	private static Class[] getParameterClass(Object[] parameter) {
		Class[] methodParameters = null;
		if (parameter != null && parameter.length > 0) {
			methodParameters = new Class[parameter.length];
			for (int i = 0; i < parameter.length; i++) {
				methodParameters[i] = parameter[i].getClass();
			}
		}
		return methodParameters;
	}

	/**
	 * 用对象反射调用它的某个方法(指定参数类型的方法)
	 * 
	 * @param className
	 *            类名
	 * @param obj
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param parameter
	 *            参数数组
	 * @param methodParameters
	 *            参数类型数组
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object invokeMethodWithObjHasSpecialParame(String className,
			Object obj, String methodName, Object[] parameter,
			Class[] methodParameters,CpuObject parameterObj) {
		Object object = null;
		try {
			Method method = Class.forName(className).getMethod(
					methodName.trim(), methodParameters);
			object = method.invoke(obj, parameter);
		} catch(Exception ex){
			parameterObj.setFindError(true);
			return object;
		}
		return object;
	}

	/**
	 * 反射获取一个类的方法信息 包括参数,方法名,返回类型
	 * 
	 * @param className
	 *            类名
	 * @return List<String>
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> getMethodMsg(String className) {
		List<String> retValue = new ArrayList<String>();
		try {
			// 通过getMethods得到类中包含的方法
			Class myClass = Class.forName(className);
			Method m[] = myClass.getDeclaredMethods();
			for (int i = 0; i < m.length; i++) {
				String meth = m[i].toString();
				// 截取出所有的参数,参数以,形式分割
				meth = meth.substring(meth.indexOf("(") + 1, meth.indexOf(")"));
				// ret由3部分构成：参数;方法名;返回类型
				String ret = meth + ";" + m[i].getName() + ";"
						+ m[i].getReturnType();
				retValue.add(ret);
			}
			return retValue;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return retValue;
	}
	
	
	public static void getMethodOfClass(Class myClass){
		
	}
	
	/**
	 * 反射获得接口类属性名的方法
	 * @param clazz		接口类
	 * @param value		类的整型属性值
	 * @return				类的属性名
	 */
	@SuppressWarnings("rawtypes")
	public static String getInterfaceFieldNameByFieldIntegerValue(Class clazz, int value) {
		Field[] fieldArray = clazz.getFields();
		for (Field field : fieldArray) {
			try {
				int fieldValue = field.getInt(field);
				if (fieldValue == value)
					return field.getName();
			} catch (Exception e) {
				continue;
			}
		}
		return null;
	}
	

	
	
	public static void main(String[] args) {
	}
	
}
