package com.yang.leetcode;


/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class FrogJumpFloor {

    public int JumpFloor(int target) {
    	if(target < 0) return -1;
    	
    	if(target <= 2) return target;
    	
    	return JumpFloor(--target) + JumpFloor(--target);
    }
    
    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    public int jumpFloor(int target) {
    	if(target < 0) return -1;
    	
    	if(target <= 2) return target;
    	
    	return jump(target);
    }
    
    public int jump(int target) {
    	if(target == 0) return 1;
    	
    	if(target <= 2) return target;
    	
    	int result = 0;
    	do{
    		result += jump(--target); 
    	}while(target > 0);
    	
    	return result;
    }
    
}
