package com.yang.lamada.function;

import java.util.function.Function;

/**
 * https://www.jianshu.com/p/8dc46a2dc21d
 * @author yangyaming
 */
public class HelloWorld {

	public static int compute(int a, Function<Integer, Integer> function) {
	    int result = function.apply(a);
	    return result;
	}
	
	
	/**
	 * 可以看到我们定义一个方法就可以实现多种功能，这就是前面说过的Lambda表达式传递的是一种行为，
	 * 我们把想要做的事在调用的时候，以一种行为的方式传递进来，程序读起来也更加直观
	 */
	public static void main(String[] args) {
		//25 计算平方
		System.out.println(compute(5,value -> value * value));
		
		//10 求和
		System.out.println(compute(5,value -> value + value));
		
		//3 
		System.out.println(compute(5,value -> value - 2));
	}
}
