package com.yang.leetcode.array;

import org.junit.Test;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例子：
 *  例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 *  由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *  
 * 解法：
 * 	  1) 找出那个可能超过数组长度的一半的数(要么是超过半数的数，在没有超过半数时，可能为其他任何数)
 *    2) 统计他所有的次数
 */
public class FindNumInArray {
	
    public int MoreThanHalfNum_Solution(int [] array) {
    	if(array == null || array.length <= 0)
    		return 0;
    	//可能为超过半数的数
    	int candidateNum = array[0];
    	int times = 1;
    	for(int num : array){
    		if(times > 0)
    			times = (num == candidateNum) ? times+1 : times-1;
    		
    		if(times <= 0){
    			candidateNum = num;
    			times = 1;	
    		}
    	}
    	
    	int maxTime = 0;
    	for(int num : array)
    		if(candidateNum == num) 
    			maxTime++;
    	
		return (2*maxTime > array.length)? candidateNum : 0;
    }
	
    @Test
    public void testCase(){
    	System.out.print(new FindNumInArray().MoreThanHalfNum_Solution(new int[]{1,1,3,0,2,2,5,4,2}));
    }
}
