package com.yang.leetcode.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

	Example:
	nums = [1, 2, 3]
	target = 4
	
	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)
	
	Note that different sequences are counted as different combinations.
	Therefore the output is 7.
	
	
 *
 */
public class CombinationSumIV {
	//这是一个很好的题目，这个题目的解法有很多种，而我写出了一种时间复杂度很差的一种
	/************************************ 第一种 *****************************************/
    private int allTimes=0;

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        combination(new ArrayList<Integer>(), nums ,target,0);
        return allTimes;
    }
    
    public void combination(List<Integer> tmp, int[] nums, int target,int start){
        if(target == 0){
            int i=0;
            int[] arr = new int[tmp.size()];
            for(Integer num :tmp)
                arr[i++] = num;

            permutation(arr,0);
            return;
        }
        
        if(start == nums.length)
            return;
        
        if(target < nums[start])
            return;
        
        for(int i=start; i<nums.length ; i++){
            tmp.add(nums[i]);
            combination(tmp, nums ,target-nums[i],i);
            tmp.remove(tmp.size() -1);
        }
    }
    
    public void permutation(int[] nums ,int start){
        if(start == nums.length){
            allTimes++;
            return;
        }
            
        Set<Integer> set = new HashSet<Integer>();
        for(int i=start ; i<nums.length ; i++){
            if(set.add(nums[i])){
                swap(nums,start,i);
                permutation(nums,start+1);
                swap(nums,start,i);
            }
        }
    }
    
    
    public void swap(int[] nums,int start,int i){
        if(start == i)
            return;
        
        int tmp= nums[i];
        nums[i] = nums[start];
        nums[start] = tmp;
    }
    /************************************ 第一种 *****************************************/
    
    /**
     * Think about the recurrence relation first. How does the # of combinations of the target related to the # of combinations of numbers that are smaller than the target?

		So we know that target is the sum of numbers in the array. Imagine we only need one more number to reach target, this number can be any one in the array, right? So the # of combinations of target, comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].
		
		In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3). As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].
		
		Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.
		
		EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.
		
		Now we can come up with at least a recursive solution.
		
		https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
     */
    /************************************ 第二种 *****************************************/
    public int combinationSum4_II(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for(int num : nums)
    		if(target >= num)
                res += combinationSum4_II(nums, target - num);
            
        return res;
    }
    /************************************ 第二种 *****************************************/
    
    
    /************************************ 第三种 *****************************************/
    public int combinationSum4_DP(int[] nums, int target) {
    	int[] dp = new int[nums.length+1];
    	Arrays.fill(dp, -1);
    	//dp[0] 表示target=1的时候有多少种情况，这里默认为1
    	dp[0] = 1;
    	
    	dp[target] = combinationSumDP(nums,dp,target);
    	return dp[target];
    }
    
    public int combinationSumDP(int[] nums,int[] dp, int target){
    	if(dp[target] != -1){
    		return dp[target];
    	}
    	int res = 0;
    	for(int num : nums)
    		if(target >= num)
    			res += combinationSumDP(nums,dp, target-num);
    	dp[target] = res;
    	return dp[target];
    }
    /************************************ 第三种 *****************************************/
    
    /************************************ 第四种 *****************************************/
    public int combinationSum4DP(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }
    /************************************ 第四种 *****************************************/
}
