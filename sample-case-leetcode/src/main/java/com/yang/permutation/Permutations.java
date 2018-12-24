package com.yang.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 题目:
 * 	给定一个数组，数组中元素的值不重复，求出所有可能的排列
 * lettcode:
 * 	https://leetcode.com/problems/permutations/
 * 
 */
public class Permutations {

	/**
	 * 第一种解法，交换法
	 */
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        track(result,nums,0);
        return result;
    }
    
    public void track(List<List<Integer>> result,int[] nums,int start){
    	//递归出口
        if(start == nums.length){
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return ;
        }
        
        for(int i=start; i<nums.length; i++){
            swap(nums,start,i);
            track(result,nums,start+1);
            swap(nums,start,i);
        }
    }
    
    public void swap(int[] nums,int start,int i){
    	if(i == start)
    		return ;
    	
        int tmp = nums[i];
        nums[i] = nums[start];
        nums[start] = tmp;
    }
    

    /**
     * 第二种解法
     */
	public List<List<Integer>> permute2(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		
		backtrack(list, new ArrayList<>(), nums);
		return list;
	}

	private void backtrack(List<List<Integer>> list, List<Integer> tempList,int[] nums) {
		if (tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
			return;
		}
	
		// 代表从nums[i]开始
		for (int i = 0; i < nums.length; i++) {
			if (tempList.contains(nums[i]))
				continue; 
			
			tempList.add(nums[i]);
			backtrack(list, tempList, nums);
			tempList.remove(tempList.size() - 1);
		}
	}
	
}
