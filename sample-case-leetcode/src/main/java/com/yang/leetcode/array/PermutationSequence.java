package com.yang.leetcode.array;

import org.junit.Test;

public class PermutationSequence {
	   
    private int kth = 0;
    public String getPermutation(int n, int k) {
        
        StringBuilder sb = new StringBuilder();
        for(int i=1; i <= n; i++)
            sb.append(i);
        
        char[] charArr = sb.toString().toCharArray();
        
        String result = null;
        backTrack(result,charArr,0, k);
        return result;
    }
    
    public void backTrack(String result,char[] charArr,int start,int k){
        if(start == charArr.length){
            kth++;
            
            if(kth == k)
                result = new String(charArr);
            
            return;
        }
        
        for(int i=start; i<charArr.length; i++){
            swap(charArr,start,i);
            backTrack(result,charArr,start+1, k);
            swap(charArr,start,i);
        }
    }
    
    public void swap(char[] nums,int start,int i){
        if(start == i)
            return;
        char tmp = nums[i];
        nums[i] = nums[start];
        nums[start] = tmp;
    }
    
    @Test
    public void testCase() {
    	new PermutationSequence().getPermutation(3,3);
	}
}
