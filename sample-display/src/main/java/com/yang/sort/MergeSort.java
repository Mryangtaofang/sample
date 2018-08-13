package com.yang.sort;

import com.yang.service.Sortable;

public class MergeSort  implements Sortable {
	
	private int[] temp = null;

	/**
	 * 将a[first, mid] 和 a[mid+1, last] 合并
	 */
	private void mergeArray(int in[], int first, int mid, int last){
	    int left = first, right=mid+1;//设置两个数组的起始边界
	    int left_end=mid, right_end=last;//设置两个数组的结束边界

	    int k=0;

	    while (left <= left_end && right<=right_end){
	        if(in[left] <= in[right]){
	            temp[k++] = in[left++];
	        }else {
	            temp[k++] = in[right++];
	        }
	    }
	    //添加剩余的元素
	    while (left<=left_end)  temp[k++] = in[left++];

	    while (right <= right_end)  temp[k++] = in[right++];
	    
	    System.arraycopy(temp, 0, in, first, k);
	}
	
	/**
	 * 二路归并  使用递归解决.
	 * @param in
	 * @param first 数组的起始下标
	 * @param last 数组的结束下标
	 * @param out 辅助数组
	 */
	public void mergeSort(int[] in, int first, int last){
	    if(first >= last) return;
	    
        int mid = (first + last)/2;

        mergeSort(in, first, mid);//左边有序
        mergeSort(in, mid+1, last);//右边有序

        mergeArray(in, first, mid, last); //再将两个有序序列合并.
	}

	@Override
	public void sort(int[] arr) {
		temp = new int[arr.length];
		mergeSort(arr,0,arr.length-1);
	}
	
	@Override
	public String toString() {
		return "MergeSort";
	}

}
