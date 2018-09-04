package com.yang.leetcode;

import org.junit.Test;

/**
 * 输入一个，输出该数二进制表示中1的个数。
 * 其中负数用补码表示。
 */
public class GetBits {
    
    /**
     * 解法一：使用(与)
     */
    public int solution_1(int n) {
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }
    
	/**
	 * 解法二：使用(无符号右移)
	 */
    public int solution_2(int n) {
    	if(n == 0) return 0;
    	
        int count = 0;
    	
        /**当n为负数，去除最高位的符号位（即-1），将n变为正数*/
    	if(n < 0){
    		count++;
    		n = (n<<1) >>> 1;
    	}
		do{
			if((n&1) == 1) count ++ ;
		}while((n = n>>>1) > 0);
		
        return count;
    }
    
    @Test
    public void testNum(){
    	System.out.print(new GetBits().solution_1(-1));
    }
    
}
