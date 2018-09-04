package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 基于二分查找的插入排序
 */
public class InsertBy2DivideSort implements Sortable{

	@Override
	public void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int insertNum = arr[i]; // 右手抓到一张扑克牌
			int right = i - 1;
			int left = 0;

			while (left <= right) {
				int mid = (left + right) / 2;
				if (arr[mid] > insertNum)
					right = mid - 1;
				else
					left = mid + 1;
			}
			int j = i - 1;
			for (; j >= left; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = insertNum;
		}		
	}
	
	@Override
	public String toString() {
		return "InsertBy2DivideSort";
	}

}
