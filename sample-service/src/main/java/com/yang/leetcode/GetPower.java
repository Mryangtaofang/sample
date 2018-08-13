package com.yang.leetcode;

import org.junit.Test;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。
 * 求base的exponent次方
 */
public class GetPower {
	public double Power(double base, int exponent) {
		if(base == 0.0d) return 0.0d;

		if(exponent == 0) return 1;

		if(exponent < 0) return (1/base) * Power(base, ++exponent);
		
		return base * Power(base, --exponent);
	}
	
	@Test
	public void testPower(){
		System.out.print(Power(-2,2));
	}
}
