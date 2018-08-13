package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 冒泡排序
 * 最差时间复杂度 ---- O(n^2)
 * 最优时间复杂度 ---- 如果能在内部循环第一次运行时,使用一个旗标来表示有无需要交换的可能,可以把最优时间复杂度降低到O(n)
 * 平均时间复杂度 ---- O(n^2)
 * http://www.cnblogs.com/eniac12/p/5332117.html
 */
public class BubbleSort implements Sortable{
	
	@Override
	public void sort(int arr[]){
		if(arr == null || arr.length <= 0) return;
		
		int lastIndex = arr.length - 1;
		
		for (int i = 0; i <= lastIndex; i++) {

			for (int j = 0; j < lastIndex - i; j++) {
				if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
			}
		}
	}
	
	public void swap(int arr[], int firstIndex, int secondIndex) {
		int temp = arr[firstIndex];
		arr[firstIndex] = arr[secondIndex];
		arr[secondIndex] = temp;
	}

	@Override
	public String toString() {
		return "BubbleSort";
	}
	
}
