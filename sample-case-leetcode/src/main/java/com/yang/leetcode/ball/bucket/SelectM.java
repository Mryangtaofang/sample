package com.yang.leetcode.ball.bucket;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.google.common.collect.Lists;

public class SelectM {

	public List<Integer> selectSomeNum(int[] array, int k){
		List<Integer> result = Lists.newArrayList();
		
		int len = array.length;
		k = (k > len) ? len : k;
		Random random = new Random();
		
		for(int i=0; i < k; i++){
			int index = random.nextInt(len-i);
			result.add(array[index]);
			array[index] = array[len-1-i];
		}
		
		return result;
	}
	
	@Test
	public void testCase(){
		
		List<Integer> s = Lists.newArrayList(2,3,4,5,6,7);
		int[] array = s.stream().mapToInt(Integer::valueOf).toArray();
		
		System.out.print(new SelectM().selectSomeNum(array,6));
	}
}
