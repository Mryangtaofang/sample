package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 计数排序的前提条件：
 * 1.计数排序用到一个额外的计数数组,而且这个数组的长度必须大于等于待排序数组中的最大值。
 * 2.通俗地理解，例如有10个年龄不同的人，假如知道有8个人的年龄不比小明大（即小于等于小明的年龄，这里也包括了小明），
 *   那么小明的年龄就排在第8位，通过这种思想可以确定每个人的位置，也就排好了序。
 * 	  当然，年龄一样时需要特殊处理（保证稳定性）：通过反向填充目标数组，填充完毕后将对应的数字统计递减，可以确保计数排序的稳定性。
 * 
 * 举个例子：
 * 待排序数组inArr为：[9,2,10,2,1,5,6,8]
 * 
 * 计数排序需要使用一个辅助的数组.
 * helpArr初始化为:[0,0,0,0,0, 0,0,0,0,0] 长度为11（即inArr数组中最大数加一）。
 * 
 * 第一步：
 * 		遍历inArr,
 * 		比如inArr中有一个9,helpArr[9] = 1;
 * 		inArr中有两个2,helpArr[2] = 2;
 * 		依次内推
 * 		使helpArr[a]=(inArr中a出现的次数);
 * 
 * 	遍历完之后结果如下:
 * 		下标	:  0 1 2 3 4  5 6 7 8 9 10
 * 	helpArr : [0,1,2,0,0, 1,1,0,1,1,1]
 * 
 * 第二步:
 * 		遍历helpArr
 * 		从i=1开始,使helpArr[i]=helpArr[i]+helpArr[i-1],
 *  	
 *  遍历完之后结果如下:
 *  	下标	:  0 1 2 3 4  5 6 7 8 9 10
 * 	helpArr : [0,1,3,3,3, 4,5,5,6,7,8]
 *	
 *	此时，helpArr[1]=1， 表示在数组inArr中， 小于等于1的数有1个
 *		helpArr[2]=3， 表示在数组inArr中， 小于等于2的数有3个
 *		helpArr[i]=j， 表示在数组inArr中， 小于等于i的数有j个
 * 
 * 第三步：创建一个和inArr长度相同的数组sortArr
 * 		遍历inArr
 * 		比如第一个数inArr[0]为9，
 * 		helpArr[9]=7，表示在数组inArr中， 小于等于9的数有7个。
 * 		将9放置到sortArr数组的第7个位置上，即sortArr[7-1] = 9;
 * 
 * sortArr : [1,2,2,5,6,8,9,10] //已经排好序
 * 
 * 		则通用公式为：
 * 		int index = helpArr[arr[i]] - 1;
 * 		sortArr[index] = arr[i]; 
 * 		helpArr[arr[i]] = index; //比如有两个相同的数，第一个数排好序之后 ，另一个数需要排在这个数的前面 
 * 
 */
public class CountSort implements Sortable{

	private static final int MAX_NUM = 10;   // 基数为100，排序[0,99]内的整数
	
	@Override
	public void sort(int[] arr) {
		int helpArr[] = new int[MAX_NUM]; 
		int len = arr.length;
		
		// 使helpArr[i]保存着等于i的元素个数
	    for (int i = 0; i < len; i++) 
	        helpArr[arr[i]]++;
	    
	    // 使helpArr[i]保存着小于等于i的元素个数，排序后元素i就放在第helpArr[i]个输出位置上
	    for (int i = 1; i < MAX_NUM; i++) 
	        helpArr[i] = helpArr[i] + helpArr[i - 1];
	    
	    int sortArr[] = new int[len];
	    
	    // 从后向前扫描保证计数排序的稳定性(重复元素相对次序不变)
	    // 把每个元素A[i]放到它在输出数组B中的正确位置上
	    // 当再遇到重复元素时会被放在当前元素的前一个位置上保证计数排序的稳定性
	    for (int i = len - 1; i >= 0; i--) 
	        sortArr[--helpArr[arr[i]]] = arr[i];      
	    
		System.arraycopy(sortArr, 0, arr, 0, len);
	}
	
	@Override
	public String toString() {
		return "CountSort";
	}

}
