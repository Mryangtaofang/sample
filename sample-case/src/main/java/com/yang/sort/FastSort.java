package com.yang.sort;

import com.yang.service.Sortable;

public class FastSort implements Sortable {

	@Override
	public void sort(int[] array) {
		if(array == null || array.length <= 0)
			return;
		
		quickSort(array,0,array.length-1);
	}
	
	/**
	 * 挖坑填数法
	 * 1.以第一个数为基数,并且取出来付给base,因此数组的第一个位置就成了一个坑
	 * 2.从数组末尾开始，找到第一个小于基数的数，并用它填第一个位置的坑，这个数的之前所在的位置就形成了一个新的坑
	 * 3.从数组head开始,找到第一个大于基数的数,并用它填原来的坑,这个数的之前所在的位置就形成了一个新的坑
	 * 4.将基数填在新的坑中。
	 * 继续循环2,3,4
	 * * */
	public void quickSort(int[] array,int start,int end){
		if(start >= end){
			return;
		}
		int base = array[start];
		int low = start; //低位指针
		int high = end;  //高位指针
		
		while (indexNotEqual(low,high)) {
			
			while(indexNotEqual(low,high) && array[high] >= base) high--;
			
			if (indexNotEqual(low,high)) {
				array[low] = array[high];// 将array[high]填到array[low]中，array[high]就形成了一个新的坑
				low++;
			}
			
			while (indexNotEqual(low,high) && array[low] < base) low++;
			
			if (indexNotEqual(low,high)) {
				array[high] = array[low];// 将array[high]填到array[low]中，array[high]就形成了一个新的坑
				high--;
			}
			array[low] = base;
		}

		quickSort(array,start,low-1);
		quickSort(array,low+1,end);
	}
	
	//不等
	boolean indexNotEqual(int low,int high){
		return low < high;
	}

	@Override
	public String toString() {
		return "FastSort";
	}
}
