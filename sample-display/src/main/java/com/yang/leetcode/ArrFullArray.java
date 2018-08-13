package com.yang.leetcode;

import java.util.Arrays;

import org.junit.Test;

/**
 * 计算数组的全排列,
 * 比如{1,2,3}
 * 打印
 * {1,2,3}
 * {2,1,3}
 * {3,1,2}
 * {3,2,1}
 * {1,3,2}
 * {2,3,1}
 */
public class ArrFullArray {

	public static int count = 0;
	public void fullArray(int inArr[]){
		get(inArr,0,inArr.length-1);
	}

	
	public void get(int inArr[],int start,int end){
		if(start >= end){
			count++;
			System.out.println(Arrays.toString(inArr));
			return;
		}
		
		for (int i = start; i <= end; i++) {
			swap(inArr,i,start);
			get(inArr,start+1,end);
			swap(inArr,start,i);
		}
	}
	
	private void swap(int[] inArr, int i, int start) {
		if(i == start || i > inArr.length)
			return;
		int temp = inArr[i];
		inArr[i] = inArr[start];
		inArr[start] = temp;
	}


	@Test
	public void testFullArray(){
		new ArrFullArray().fullArray(new int[]{1,2,3,4,5,6,7});
		System.out.println("总数:"+count);
	}
}
