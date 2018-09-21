package com.yang.leetcode.array;

import org.junit.Test;


public class FindSingleNum {
	
	
	/**
	 * 给一个数组，其中每一个数都出现了2次，但是有一个只出现了一次，找出这个数。
	 *
	 * 解法： 异或
	 */
    public int singleNumber4Two(int[] array) {
        if(array == null || array.length <= 0)
        	return -1;
        
        int singleNum = 0;
        for(int num : array)
        	singleNum = singleNum^num;
        
        return singleNum;
    }
    
    
    
    /***
     * 给一个数组，其中每一个数都出现了3次，但是有一个只出现了一次，找出这个数。
     * 
     * 解法：
     * Single Number的本质，就是用一个数记录每个bit出现的次数，如果一个bit出现两次就归0，
     * 这种运算采用二进制底下的位操作^是很自然的。Single Number II中，如果能定义三进制底下的某种位操作，
     * 也可以达到相同的效果，Single Number II中想要记录每个bit出现的次数，一个数搞不定就加两个数，
     * 用ones来记录只出现过一次的bits，用twos来记录只出现过两次的bits，ones&twos实际上就记录了出现过三次的bits，
     * 这时候我们来模拟进行出现3次就抵消为0的操作，抹去ones和twos中都为1的bits。
     * 	
     */
    public int singleNumber4Three(int[] array) {
        int ones = 0; //记录只出现过1次的bits
        int twos = 0; //记录只出现过2次的bits
        int threes = 0;
        for(int num : array){
            twos |= num & ones;
            ones ^= num;
            threes = ones & twos; //ones和twos中都为1即出现了3次
            ones &= ~threes; //抹去出现了3次的bits
            twos &= ~threes;
        }
        return twos;
    }
    
    @Test
    public void testCase(){
    	System.out.print(new FindSingleNum().singleNumber4Three(new int[]{1,2,1,4,4,1,2,2}));
    }
}
