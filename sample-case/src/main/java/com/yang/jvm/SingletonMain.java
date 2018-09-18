package com.yang.jvm;


/**
 * # 与类加载相关的一个非常有意思的case
 * 
 * Singleton1与Singleton2,只是定义3个变量的顺序不一样而已，但是结果确不一样,如下:
 *     public static int value1;
 *     public static int value2 = 0;
 *	   private static Singleton2 singleton = new Singleton2();
 *
 *	Singleton输出结果：1 0 
 *	原因：
 *	
 *	1. 首先执行main中的Singleton singleton = Singleton.getInstance(); 
 *	2. 类的加载：加载类Singleton 
 *	3. 类的验证 
 *	4. 类的准备：为静态变量分配内存，设置默认值。这里为singleton(引用类型)设置为null,value1,value2（基本数据类型）设置默认值0 
 *	5. 类的初始化（按照赋值语句进行修改）： 
 *		执行private static Singleton singleton = new Singleton(); 
 *		执行Singleton的构造器：value1++;value2++; 此时value1，value2均等于1 
 *		执行 
 *		public static int value1; 
 *		public static int value2 = 0; 
 *		此时value1=1，value2=0
 */
public class SingletonMain {

	public static void main(String[] args){
	    Singleton1.getInstance();
	    System.out.println("Singleton1 value1:" + Singleton1.value1);
	    System.out.println("Singleton1 value2:" + Singleton1.value2);

	    Singleton2.getInstance();
	    System.out.println("Singleton2 value1:" + Singleton2.value1);
	    System.out.println("Singleton2 value2:" + Singleton2.value2);
	}
}

/**
 *	Singleton1输出结果：1 0 
 *	原因：
 *	
 *	1. 首先执行main中的Singleton singleton = Singleton.getInstance(); 
 *	2. 类的加载：加载类Singleton 
 *	3. 类的验证 
 *	4. 类的准备：为静态变量分配内存，设置默认值。这里为singleton(引用类型)设置为null,value1,value2（基本数据类型）设置默认值0 
 *	5. 类的初始化（按照赋值语句进行修改）： 
 *		执行private static Singleton singleton = new Singleton(); 
 *		执行Singleton的构造器：value1++;value2++; 此时value1，value2均等于1 
 *		执行 
 *		public static int value1; 
 *		public static int value2 = 0; 
 *		此时value1=1，value2=0
 */


/**
 * 
 * 1 首先执行main中的Singleton2 singleton2 = Singleton2.getInstance2(); 
 * 2 类的加载：加载类Singleton2 
 * 3 类的验证 
 * 4 类的准备：为静态变量分配内存，设置默认值。这里为value1,value2（基本数据类型）设置默认值0,singleton2(引用类型)设置为null, 
 * 5 类的初始化（按照赋值语句进行修改）： 
 * 执行 
 * public static int value2 = 0; 
 * 此时value2=0(value1不变，依然是0); 
 * 执行 
 * private static Singleton singleton = new Singleton(); 
 * 执行Singleton2的构造器：value1++;value2++; 
 * 此时value1，value2均等于1,即为最后结果
 */

