package com.yang.alibaba;

import java.util.function.BiFunction;

public class LamadaP {

	public static Integer rest(BiFunction<Integer, Double, Integer> takeABreak) {
		return takeABreak.apply(3, 10.2);
	}

	public static void main(String[] participants) {
		rest(  (s, e) -> s.intValue() + e.intValue()        );
		//(s,w) -> 2*w
		//(n,w,e) -> System.out::print
		//(int n, double e) -> (int)(n+e)
	}
}
