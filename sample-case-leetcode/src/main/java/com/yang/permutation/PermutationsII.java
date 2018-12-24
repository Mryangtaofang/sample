package com.yang.permutation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;


/**
 * 题目:
 * 	给定一个数组，数组中元素的值可以重复，求出所有可能的排列
 * lettcode:
 * 	https://leetcode.com/problems/permutations-ii/
 */
public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = Lists.newArrayList();
        backtrack(result,nums,0);
        return result;
    }
    
    private void backtrack(List<List<Integer>> result,int[] nums,int start){
        if(start == nums.length){
            result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        
        Set<Integer> appeared = new HashSet<>();
        for(int i=start ; i<nums.length ; i++){
            if(appeared.add(nums[i])){
                swap(nums,start,i);
                backtrack(result,nums,start+1);
                swap(nums,start,i); 
            }            
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
}
