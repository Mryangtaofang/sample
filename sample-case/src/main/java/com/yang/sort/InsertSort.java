package com.yang.sort;

import com.yang.service.Sortable;

public class InsertSort implements Sortable {

	/**
	 * 普通的插入排序
	 */
	@Override
	public void sort(int[] arr) {
	    for (int i = 1; i < arr.length; i++){// 类似抓扑克牌排序
	        int temp = arr[i]; // 右手抓到一张扑克牌
	        int j = i - 1;
	        for( ;j >= 0 && arr[j] > temp;j--){
	        	arr[j + 1] = arr[j]; 
	        }
	        arr[j + 1] = temp; // 直到该手牌比抓到的牌小(或二者相等)，将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的)
	    }
	}
	
}
