package com.yang.leetcode.dp;

import org.junit.Test;

/**
 * 
 * @author yangyaming
 *
 */
public class LongestIncrSubsequence {

    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length <= 0)
            return 0;
        
        int len = nums.length;
        //local[i]表示nums到i为止，最后一个元素是nums[i]的最大增长子串的长度
        int[] local = new int[len];
        int[] longest = new int[len];
        local[0] = 1;
        longest[0] = 1;
        
        for(int i=1; i<len; i++){
            
            int max = 1;
            for(int j=i; j>0; j--){
                if(nums[i] > nums[j-1])
                    max = Math.max(local[j-1] + 1, max);
            }
            local[i] = max;
            
            longest[i] = Math.max(longest[i-1], local[i]);
        }
        
        return longest[len-1];
    }
    
    @Test
    public void testCase(){
    	new LongestIncrSubsequence().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }
}
