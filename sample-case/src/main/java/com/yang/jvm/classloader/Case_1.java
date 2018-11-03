package com.yang.jvm.classloader;

/**

 *	静态块：用static申明，JVM加载类时执行，仅执行一次
	构造块：类中直接用{}定义，每一次创建对象时执行
	
	执行顺序优先级：静态块>main()>构造块>构造方法
	
	静态块按照申明顺序执行，所以先执行publicstaticB t1 = newB();该语句创建对象，则又会调用构造块，输出构造块
	
	接着执行public static B t1 = new B();输出构造块
	再执行
	static
	{
		System.out.println("静态块");
	}输出静态块
	最后main方法执行，创建对象，输出构造块。
	
	静态块和静态属性优先执行，谁在前就先执行谁
 * @author yangyaming
 *
 */
public class Case_1 {
	public static Case_1 t1 = new Case_1();
	public static Case_1 t2 = new Case_1();
	{
		System.out.println("构造块");
	}
	static {
		System.out.println("静态块");
	}

	public static void main(String[] args) {
		new Case_1();
	}
}
