package com.yang.bit;

import org.junit.Test;

public class BitTest {

	@Test
	public void unsignBit(){
//		int n = -6;
//		System.out.println((-7)|(-7)>>>1);
//		n = n |(n>>>1);
//		System.out.println(n);
//		System.out.println(tableSizeFor(-7));
//		System.out.println(tableSizeFor(10));
//		System.out.println(tableSizeFor(65));
//		System.out.println("yang".equals(new String("yang").intern()));
//		System.out.println((-2)>>>1);
//		System.out.println((-2)>>1);
//		System.out.println(1<<30);
//		System.out.println(Integer.MAX_VALUE);
		
	}
	
	
    final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }
    
    
    
}
