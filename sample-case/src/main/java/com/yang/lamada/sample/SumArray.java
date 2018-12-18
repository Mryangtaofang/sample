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
	
	
	@Test
	public void testCase(){
		System.out.println(new SumArray().sum(new int[]{1,2,3,4}));;
	}
}
