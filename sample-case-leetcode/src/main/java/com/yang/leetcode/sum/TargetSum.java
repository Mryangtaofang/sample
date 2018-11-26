package com.yang.leetcode.sum;

import org.junit.Test;

/**
 * https://leetcode.com/problems/target-sum/
 * @author yangyaming
 */
public class TargetSum {

//	public int findTargetSumWays(int[] nums, int s) {
//		int sum = 0;
//		for (int num : nums)
//			sum += num;
//		
//		if (s > sum || s < -sum)
//			return 0;
//		
//		int[] dp = new int[2 * sum + 1];
//		dp[0 + sum] = 1;
//		
//		for (int i = 0; i < nums.length; i++) {
//			int[] next = new int[2 * sum + 1];
//			for (int k = 0; k < 2 * sum + 1; k++) {
//				if (dp[k] != 0) {
//					next[k + nums[i]] += dp[k];
//					next[k - nums[i]] += dp[k];
//				}
//			}
//			dp = next;
//		}
//		return dp[sum + s];
//	}
    
	
	public int findTargetSumWays(int[] nums, int s) {
		int sum = 0;
		for (int n : nums)
			sum += n;
		
		//判断s + sum的奇偶，如果s+sum为奇数，则不存在
		if (sum < s || (s+sum) % 2 > 0)
			return 0;
		
		return subsetSum(nums,(s + sum) >>> 1);
	}

	public int subsetSum(int[] nums, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int n : nums)
			for (int i = s; i >= n; i--)
				dp[i] += dp[i - n];
		return dp[s];
	}
	
    
    /******************** Test Case ********************/
    @Test
    public void testCase(){
    	new TargetSum().findTargetSumWays(new int[]{1,1,1,1,1},3);
    }
}
