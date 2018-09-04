package com.yang.leetcode;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 * @author yangyaming
 */
public class FindNumInArray {
	
    public int MoreThanHalfNum_Solution(int [] array) {
    	if(array == null || array.length <= 0)
    		return 0;
    	
    	int currentNum = array[0];
    	int times = 1;
    	for(int i=0; i<array.length ; i++){
    		if(times > 0)
    			times = (array[i] == currentNum) ? times+1 : times-1;
    		
			currentNum = array[i];
			times = 1;
			continue;
    	}
    	
    	int maXtime = 0;
    	for(int i=0;i < array.length;i++)
    		if(currentNum == array[i]) 
    			maXtime++;
		return (2*maXtime > array.length)? currentNum : 0;
    }
	
}
