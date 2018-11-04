package com.yang.lamada.function;

import java.util.function.Function;

/**
 * Function compose方法
 * compose方法是一个默认方法，这个方法接收一个function作为参数，
 * 将参数function执行的结果作为参数给调用的function，以此来实现两个function组合的功能
 *
 */
public class Compose {

	/**
	 * compose方法内部代码是：
	 *
	 * return (V v) -> apply(before.apply(v));
	 * 
	 * 返回的是一个Function，输入一个参数，返回一个参数值，这个Function 在调用apply时首先执行的是 before.apply(v)
	 * before在这里就是value -> value * value，也就是 2*2，
	 * 	将得到的结果4，
	 * 作为参数传递给function1，在这里就是value -> value * 3 ，
	 * 	所以结果是：12
	 */
	public static int  compute(int num, Function<Integer, Integer> first, Function<Integer, Integer> second) {
	    return first.compose(second).apply(num);
	}
	
	public static int computeAndThen(int num, Function<Integer, Integer> first, Function<Integer, Integer> second) {
	    return first.andThen(second).apply(num);
	}


	public static void main(String[] args) {
		System.out.println(compute(2, value -> value + 3, value -> value * value));
		System.out.println(computeAndThen(2, value -> value + 3, value -> value * value));
	}
}
