package com.yang.sort;

import java.util.Arrays;

import org.junit.Test;

import com.yang.service.Sortable;

public class TestSort {

	@Test
	public void testCase(){
//		//冒泡排序
//		sort(new BubbleSort());
//		//插入排序
//		sort(new InsertSort());
//		//选择排序
//		sort(new SelectionSort());
//		//堆排序
//		sort(new HeapSort());
//		//归并排序
//		sort(new MergeSort());
//		//快速排序
//		sort(new FastSort());
		//计数排序
		sort(new CountSort());
	}
	
	public void sort(Sortable sort){
		int arr[] = createArray();
		sort.sort(arr);
		System.out.println(sort + ": "+Arrays.toString(arr));
	}
	
	private int[] createArray(){
		return new int[]{9,8,7,6,5,4,3,2,1};
	}
}
