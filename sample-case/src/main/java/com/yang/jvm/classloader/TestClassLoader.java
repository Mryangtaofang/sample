package com.yang.jvm.classloader;

//import java.lang.reflect.Method;


/**
 * 这里WaitLoadClass为我们需要加载的类,但是它不能放在这个工程中
 * WaitLoadClass字节码所在的文件地址为:D:/com.yang.jvm/classloader/WaitLoadClass.class
 * 
 * blog:http://www.importnew.com/24036.html
 */
public class TestClassLoader {
	
	public static void main(String[] args) throws Exception{
//        MyClassLoader classLoader = new MyClassLoader("D:/");
//        Class<?> clazz = classLoader.loadClass("com.yang.jvm.classloader.WaitLoadClass");
//        Object obj = clazz.newInstance();
//        Method showLoader = clazz.getDeclaredMethod("showLoader", null);
//        showLoader.invoke(obj, null);
	}
	
	/***
	 * WaitLoadClass定义如下
	 * 
	 * public class WaitLoadClass {
     *
	 *		public void showLoader() {
	 *			System.out.println("加载我的classloader为： " + getClass().getClassLoader().getClass());
	 *		}
	 *	}
	 */
}
