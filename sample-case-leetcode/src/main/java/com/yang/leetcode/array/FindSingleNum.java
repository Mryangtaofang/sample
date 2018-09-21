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
     * 因为出现的是三次，所以需要设计成一个数出现3次之后自动会变为0，这就代表不影响后面的数
     * 现在只用bit来表示，a，b可能是0,1的组合，num(next bit)可能是0或者1
     * 采用两位a,b来表示.  a ，b 的值可能为0 0 ，0 1 ，10
 	 *
     * 分别代表0次，1次，2次。不可能出现1 1。因为3次之后就清0了
 	 * 当对应不同的数字时，无非就是把一位的情况扩展到了32位。但逻辑运算（对于每一位的仍然一样）
     */
    public int singleNumber4Three(int[] array) {
        int ones = 0; //记录只出现过1次的bits
        int twos = 0; //记录只出现过2次的bits
        int newOnes = 0;
        for(int num : array){
        	newOnes = (~ones & twos & num) | (ones & ~twos & ~num); 
        	twos = (~ones & ~twos & num) | (~ones & twos & ~num);
        	ones = newOnes;
        }
        return ones|twos;
    }
    
    /**
     * Single Number的本质，就是用一个数记录每个bit出现的次数，
     * 如果一个bit出现两次就归0，这种运算采用二进制底下的位操作^是很自然的。
     * Single Number II中，如果能定义三进制底下的某种位操作，也可以达到相同的效果，
     * Single Number II中想要记录每个bit出现的次数，一个数搞不定就加两个数，用ones来记录只出现过一次的bits，
     * 用twos来记录只出现过两次的bits，ones&twos实际上就记录了出现过三次的bits，这时候我们来模拟进行出现3次就抵消为0的操作，
     * 抹去ones和twos中都为1的bits。
     */
    public int singleNumber(int[] array) {
        int ones = 0; //记录只出现过1次的bits
        int twos = 0; //记录只出现过2次的bits
        int threes = 0; //ones&twos实际上就记录了出现过三次的bits
        for(int num : array){
            twos |= num & ones;
            ones ^= num;
            threes = ones & twos;
            //抹去ones和twos中都为1的bits。
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones|twos;
    }
    
    @Test
    public void testCase(){
    	System.out.print(new FindSingleNum().singleNumber4Three(new int[]{1,2,1,4,4,1,2,2}));
    }
}
