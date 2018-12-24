package com.yang.permutation;

import java.util.stream.IntStream;

import org.junit.Test;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * @author yangyaming
 */
public class PermutationSequence {
	public String getPermutation(int n, int k) {
		//求阶乘， 如果n=4,factorial = (n-1)! = 3*2*1 = 6;
		int factorial = (n <= 2) ? n : IntStream.range(1, n).reduce((total,i)->(total*i)).getAsInt();

		StringBuilder nums = new StringBuilder("123456789");
		StringBuilder sb = new StringBuilder();
		k--;
		for (int i = n - 1; i > 0; i--) {
			// k = 8 , index = 8/6 = 1;
			int index = k / factorial;
			// k = 8%6 = 2
			k = k % factorial;
			sb.append(nums.charAt(index));
			nums.deleteCharAt(index);

			// 6/3 = 2
			factorial = factorial / i;
		}
		sb.append(nums.charAt(0));
		return sb.toString();
	}
	
	@Test
	public void testCase(){
		int factorial = 1;
		int n = 4;
		factorial = IntStream.range(1, n).reduce((total,i)->(total*i)).getAsInt();
		System.out.println(factorial);
		System.out.println(IntStream.range(1, n).toArray().length);
		factorial = 1;
		for (int i = n - 1; i > 1; i--)
			factorial = factorial * i;
		
		System.out.println(factorial);
	}
}
