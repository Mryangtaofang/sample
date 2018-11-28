package com.yang.num;

import org.junit.Test;


/**
 * 题目：一个排好序的数组，找出两数之和为m 的所有组合。
 * 
 * 解法：定义两个指针(start和end),分别指向数组的开头和结尾，两个数相加.
 * 1.如果小于m,start右移(start++)
 * 2.如果大于m,end左移(end--)
 * 3.如果等于m,start右移 并且 end左移
 * 直到start和end相遇。
 * 
 * 例子:假设m=10,数组为sortArr：[0,1,2,3,4,5,6,7,8,9],长度为10
 * 分别设两个指针。start=0,end=9。
 * 
 * 1)第一步：
 * 	sortArr:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *  		 ^                          ^
 *  		 |		                    |
 *         start                       end
 *         
 *  action: sortArr[start]+sortArr[end] = 9 < 10,start指针右移，end指针不变。
 *  
 * 2)第二步：
 * 	sortArr:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *  		    ^                       ^
 *  		    |	                    |
 *            start                    end
 *            
 *  action: sortArr[start]+sortArr[end] = 10 = 10,start指针右移并且 end左移。
 *  
 *  
 *  3)第三步：
 * 	sortArr:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *  		       ^                 ^
 *  		       |	             |
 *               start              end
 *               
 *  action: sortArr[start]+sortArr[end] = 10 = 10,start指针右移并且 end左移。
 *  
 *                        ...
 *                        
 *  6)第六步：
 * 	sortArr:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
 *  		                ^                  
 *  		                |	             
 *                      start=end
 *               
 *  action: start=end，停止。                  
 * 
 */
public class FindNumInSortList {

	public void findAllNum(int sortArr[] , int m){
		if(sortArr == null || sortArr.length <=0)
			return ;
		
		int start = 0;
		int end = sortArr.length-1;
		
		while(end > start){
			if(sortArr[end] + sortArr[start] == m){
				System.out.println("[" + sortArr[start] + "," + sortArr[end] + "]");
				start++;
				end--;
			}else if(sortArr[end] + sortArr[start] > m){
				end--;
			}else{
				start++;
			}
		}
	}
	
	@Test
	public void testCase(){
		int[] sortArr = new int[]{1,2,3,4,5,6,7,8,9};
		new FindNumInSortList().findAllNum(sortArr, 9);
	}
	
}
