package com.yang.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * @author yangyaming
 * 取负法
 * 	如：nums = [5,1,2,2,3]
 *  取负之后
 * 	  nums = [-5,-1,-2,2,-3]
 */
public class FindDisappearedNums {

	public List<Integer> solution(int[] nums){
		List<Integer> result = new ArrayList<>();
		
        if(nums == null || nums.length == 0)
            return result;
        
        for(int i=0; i < nums.length; i++){
        	int index = Math.abs(nums[i]) - 1;
        	
        	if(nums[index] > 0)
        		nums[index] = -nums[index]; 
        }
        
        for(int i=0; i < nums.length; i++)
        	if(nums[i] > 0)
        		result.add(i+1);
        
        return result;
	}
}
