package com.yang.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 */
public class SubSetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        track(result,new ArrayList<Integer>(),nums ,0);
        return result;
    }
    
    public void track(List<List<Integer>> result,List<Integer> tmp,int[] nums,int start){
        result.add(new ArrayList<Integer>(tmp));
        
        Integer remove = null;
        for(int i=start; i<nums.length ; i++){
            if(remove != null && remove == nums[i])
                continue;
            tmp.add(nums[i]);
            track(result,tmp,nums,i+1);
            remove = tmp.remove(tmp.size()-1);
        }
        
    }
    
    public static void main(String args[]){
    	char a = 'C'+('b'-'B');

    }
}
