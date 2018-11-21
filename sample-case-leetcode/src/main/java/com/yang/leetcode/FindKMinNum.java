package com.yang.leetcode;

import java.util.ArrayList;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class FindKMinNum {
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> result = new ArrayList<Integer>();
    	if(k <= 0 || input == null 
    			|| input.length <= 0 || k > input.length)
    		return result;
    	
    	int maxIndex = -1;
    	
    	for(int num : input){
    		if(result.size() < (k-1))
    			result.add(num);
    		else if(result.size() == (k-1)){
    			result.add(num);
    			maxIndex = getMaxNumIndex(result);
    		}else if(result.get(maxIndex) > num){
    			result.set(maxIndex, num);
    			maxIndex = getMaxNumIndex(result);
    		}
    	}
    	return result;
    }

	private int getMaxNumIndex(ArrayList<Integer> result) {
		int maxIndex = 0;
		int maxNum = result.get(maxIndex);
		int size = result.size();
		for (int i = 1; i < size; i++) {
			if (maxNum < result.get(i)) {
				maxIndex = i;
				maxNum = result.get(i);
			}
		}
		return maxIndex;
	}
}
