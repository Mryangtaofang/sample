package com.yang.test.utils;

import org.junit.Test;


public class TestUtils {

	
	public static void printArray(int arr[]){
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if((i+1)<arr.length){
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}
	
	@Test
	public void testBit(){
		System.out.println("yang".hashCode());
		
		int a = -2;
//		a = (a>>>1);
		
		System.out.println(Integer.toBinaryString(a));
	}
}
