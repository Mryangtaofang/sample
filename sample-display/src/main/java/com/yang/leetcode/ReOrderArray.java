package com.yang.leetcode;

import java.util.Arrays;

import org.junit.Test;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class ReOrderArray {

	/**
	 * 类似与冒泡排序的解法，时间复杂度O(n)
	 * @param array
	 */
    public void reOrderArray(int [] array) {
        if(array == null || array.length <= 1){ return;}
        int len = array.length ;
        
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j > i; j--) {
				if (!isEven(array[j]) && isEven(array[j - 1])) {// 前面是偶数，后面是奇数，交换
					swap(array, j, j - 1);
				}
			}
		}
    }
    
    public void swap(int [] array,int firstIndex,int secondIndex){
    	int temp = array[firstIndex];
    	array[firstIndex] = array[secondIndex];
    	array[secondIndex] = temp;
    }
    
    public boolean isEven(int num){
    	return (num&1) == 1 ? false :true;
    }
    
    /**
     * 牺牲增加空间,来降低时间复杂度
     * @param array
     */
    public void reOrderArray1(int [] array) {
        if(array == null || array.length <= 1){ return;}
        int len = array.length ;
        
        int[] newArray = new int[len];
        int oddIndex = 0;
        int evenIndex = len-1;
        
		for (int i = 0; i < len; i++) {
			//如果是偶数则添加在数组的头部
			if(!isEven(array[i])){
				newArray[oddIndex] = array[i];
				oddIndex++;
			}
			
			//如果是奇数则添加在数组的尾部
			if(isEven(array[len-i-1])){
				newArray[evenIndex] = array[len-i-1];
				evenIndex--;
			}
		}
		//这个要比for快上3倍
		System.arraycopy(newArray, 0, array, 0, array.length);
    }
    
    @Test
    public void testArr(){
    	int[] array = {1,2,3,4,5,6,7};
    	reOrderArray1(array);
    	System.out.print(Arrays.asList(array).toString());
    }
}
