package com.yang.leetcode;

import org.junit.Test;


/**
 * 一个排好序的数组，找出两数之和为m 的所有组合
 * @author yangyaming
 */
public class FindNum4SortList {

	public void findAllNum(int sortArr[] , int m){
		if(sortArr == null || sortArr.length <=0)
			return ;
		
		int start = 0;
		int end = sortArr.length-1;
		
		while(end > start){
			if(sortArr[end] + sortArr[start] == m){
				System.out.println("[" + sortArr[start] + "," + sortArr[end] + "]");
				start++;
				end--;
			}else if(sortArr[end] + sortArr[start] > m){
				end--;
			}else{
				start++;
			}
		}
	}
	
	@Test
	public void testCase(){
		int[] sortArr = new int[]{1,2,3,4,5,6,7,8,9};
		new FindNum4SortList().findAllNum(sortArr, 9);
	}
	
}
