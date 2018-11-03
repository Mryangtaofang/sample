package com.yang.spring.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * 来看看自定义的注解
 * http://www.importnew.com/17413.html
 * 
 *	Java注解解析
 *		我们将使用反射技术来解析java类的注解。
 *		那么注解的RetentionPolicy应该设置为RUNTIME,
 *		否则java类的注解信息在执行过程中将不可用,那么我们也不能从中得到任何和注解有关的数据。
 *
 *	@author yangyaming
 */
public class AnnotationParsing {

	 
	public static void main(String[] args) {
	    try {
	    	//反射获取
	    	Method[] methods = Class.forName("com.yang.spring.annotation.TestAnnotation").getMethods();
	    	
	    	//Method[] methods =  AnnotationParsing.class.getClassLoader().loadClass(("com.journaldev.annotations.AnnotationExample")).getMethods();
			for (Method method : methods) {
				// 检验该方法否是MethodInfo注解
				if (method.isAnnotationPresent(com.yang.spring.annotation.MethodInfo.class)) {
					try {
						// iterates all the annotations available in the method
						for (Annotation anno : method.getDeclaredAnnotations()) 
							System.out.println("Annotation in Method  method  : " + anno);
						

						MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
						if (methodAnno.revision() == 1) 
							System.out.println("Method with revision no 1 = "+ method);

					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
	    } catch (SecurityException | ClassNotFoundException e) {
	            e.printStackTrace();
	    }
	}
	 
}

