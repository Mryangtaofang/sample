package com.yang.lamada.sample;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * 使用lamada表达式求数组的和
 * @author yangyaming
 */
public class SumArray {

	public int sum(int[] nums){
		return IntStream.of(nums).reduce((total,cost)-> total + cost).getAsInt();
	}
	
	/**
	 * IntStream自带sum函数
	 */
	public int sum2(int[] nums){
		return IntStream.of(nums).sum();
	}
	
	@Test
	public void testCase(){
		
		int[] array = new int[100000000];
		for(int i=0; i<array.length; i++)
			array[i] = i+1;
		
		
		SumArray sumArray = new SumArray();
		long timeStart = System.currentTimeMillis();
		System.out.println("sum和为：" + sumArray.sum(array));
		long sumEnd = System.currentTimeMillis();
		System.out.println("sum2和为：" + sumArray.sum2(array));
		long sum2End = System.currentTimeMillis();
		
		System.out.println("sum耗时：" +(sumEnd-timeStart));
		System.out.println("sum2耗时：" +(sum2End-sumEnd));
	}
}
