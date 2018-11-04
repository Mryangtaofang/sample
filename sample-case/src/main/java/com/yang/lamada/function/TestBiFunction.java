package com.yang.lamada.function;

import java.util.function.BiFunction;

/**
 * 了解了Function以后，有的小伙伴可能会问了，Function只能接收一个参数，
 * 如果我要传递两个参数呢，这一点Java8也替我们考虑到了，就是我们截下来要讲到的 BiFunction,
 * @author yangyaming
 */
public class TestBiFunction {
	public static int computeBiFunction(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
		return biFunction.apply(a, b);
	}

	public static void main(String[] args) {
		System.out.println(computeBiFunction(2, 3, (v1, v2) -> v1 + v2));
		System.out.println(computeBiFunction(2, 3, (v1, v2) -> v1 - v2));
		System.out.println(computeBiFunction(2, 3, (v1, v2) -> v1 * v2));
	}
}
