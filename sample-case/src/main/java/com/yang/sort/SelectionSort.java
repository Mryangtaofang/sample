package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 简单选择排序
 * @author yangyaming
 */
public class SelectionSort implements Sortable{

	@Override
	public void sort(int array[]){
		if(array == null || array.length <= 0){
			return;
		}
		
		for (int i = 0; i < array.length-1; i++) {
			int temp = array[i];
			int key = i;
			for (int j = i + 1; j < array.length; j++) {
				if(temp > array[j]){
					temp = array[j];
					key = j;
				}
			}
			if(key != i){
				array[key] = array[i];
				array[i] = temp;
			}
		}
	}

	@Override
	public String toString() {
		return "SelectionSort";
	}
}
