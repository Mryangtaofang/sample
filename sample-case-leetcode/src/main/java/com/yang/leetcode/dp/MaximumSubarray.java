package com.yang.leetcode.dp;


/**
 * 给一个int类型的数组，求是和最大的子数组，这个子数组的和为多少？
 * https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        //dp[i]表示数组最后一个元素下标为i的最大子数组的和
        int[] dp = new int[nums.length]; 
        dp[0] = nums[0];
        int max = dp[0];
        for(int i=1; i<nums.length ;i++){
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
            
            if(max < dp[i])
                max = dp[i];
        }
        
        return max;
    }
    
    public int maxSubArrayBetter(int[] nums) {
        int last = nums[0];
        int cur ;
        int max = last;
        for(int i=1; i<nums.length ;i++){
            cur = Math.max(nums[i], nums[i] + last);
            
            if(max < cur)
                max = cur;
            
            last = cur;
        }
        
        return max;
    }
}
