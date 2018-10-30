package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 在看基数排序之前，必须先懂计数排序,详见@com.yang.sort.CountSort
 * 总结：计数排序的思想是借助一个辅助的数组，统计，
 * 
 * 
 */
public class RadixSort implements Sortable{

	final int dn = 3;//待排序的元素为三位数及以下
	final int MAX_NUM = 10;//基数为10，每一位的数字都是[0,9]内的整数
	

	/**
	 * 获得数字x的第d位数字
	 * 比如x=301,d=1,则返回1
	 */
	int GetDigit(int x, int d){ 
	    int[] radix = { 1, 1, 10, 100 };//最大为三位数，所以这里只要到百位就满足了
	    return (x/radix[d]) % 10;
	}

	void CountingSort(int arr[], int d) {// 依据元素的第d位数字，对A数组进行计数排序

		int helpArr[] = new int[MAX_NUM];
		int len = arr.length;

		for (int i = 0; i < len; i++)
			helpArr[GetDigit(arr[i], d)]++;

		for (int i = 1; i < MAX_NUM; i++)
			helpArr[i] = helpArr[i] + helpArr[i - 1];

		int sortArr[] = new int[len];
		// 当再遇到当前位数字同为dight的元素时，会将其放在当前元素的前一个位置上保证计数排序的稳定性
		for (int i = len - 1; i >= 0; i--) {
			int dight = GetDigit(arr[i], d); // 元素A[i]当前位数字为dight
			sortArr[--helpArr[dight]] = arr[i]; // 根据当前位数字，把每个元素A[i]放到它在输出数组B中的正确位置上
		}

		System.arraycopy(sortArr, 0, arr, 0, len);
	}
	
	@Override
	public void sort(int[] arr) {
		// 从低位到高位
		for (int d = 1; d <= dn; d++)
			CountingSort(arr, d); //依据第d位数字对A进行计数排序
	}

}

/**
 * 基数排序的时间复杂度是O(n * dn)，其中n是待排序元素个数，dn是数字位数。
 * 这个时间复杂度不一定优于O(n log n)，dn的大小取决于数字位的选择（比如比特位数），
 * 和待排序数据所属数据类型的全集的大小；
 * dn决定了进行多少轮处理，而n是每轮处理的操作数目。
 *
　* 如果考虑和比较排序进行对照，基数排序的形式复杂度虽然不一定更小，
 * 但由于不进行比较，因此其基本操作的代价较小，而且如果适当的选择基数，dn一般不大于log n，
 * 所以基数排序一般要快过基于比较的排序，比如快速排序。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序并不是只能用于整数排序。
 * 
 * 
 * 基数排序
 * 分类 ------------- 内部非比较排序
 * 数据结构 ---------- 数组
 * 最差时间复杂度 ---- O(n * dn)
 * 最优时间复杂度 ---- O(n * dn)
 * 平均时间复杂度 ---- O(n * dn)
 * 所需辅助空间 ------ O(n * dn)
 * 稳定性 ----------- 稳定
 */








