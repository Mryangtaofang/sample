package com.yang.num;

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
	
    /**
     * 这种解法明显更好
     * 二分法，加上递归
     */
    public double myPow(double x, int n) {
        if(x == 0.0)
            return 0;
        if(n == 0)
            return 1;
     
        if(n == 1)
            return x;
        
        if(n == -1)
            return (1/x);
        
        int half = n%2;

        return (half == 1 || half == -1) ? myPow(x*x,n/2) * myPow(x,half) : myPow(x*x,n/2);
    }
    
	
	@Test
	public void testPower(){
		System.out.print(Power(-2,2));
	}
}
