package com.yang.sort;

import com.yang.service.Sortable;

/**
 * 
 * 基本概念：希尔排序按其设计者希尔（Donald Shell）的名字命名，它是一种基于插入排序的快速排序算法，要了解希尔排序，必须先掌握插入排序的原理与实现。
 * 
 * 希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大步。
 * 然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排好的了（此时插入排序较快）。
 * 步长的选择是希尔排序的重要部分。只要最终步长为1任何步长序列都可以工作（且步长要小于数组长度）。
 * 算法最开始以一定的步长进行排序。然后会继续以一定步长进行排序，最终算法以步长为1进行排序。当步长为1时，算法变为插入排序，这就保证了数据一定会被排序。
 * 
 * 简单例子分析：
 * 
 * 待排序数组：
 * {6, 5, 3, 1, 8, 7, 2, 4, 9, 0}
 * 
 * 第一次步长h=4,
 * 那么数组按照步长可以拆分成4个小数组（[0]6的意思是下标[0]的值为6）
 * 
 * {[0]6, [4]8, [8]9}
 * {[1]5, [5]7, [9]0}
 * {[2]3, [6]2}
 * {[3]1, [7]4}
 * 
 * 对这4个小数组分别进行插入排序后，4个小数组变成：
 * {[0]6, [4]8, [8]9}
 * {[1]0, [5]5, [9]7}
 * {[2]2, [6]3}
 * {[3]1, [7]4}
 * 合并起来就是：{6, 0, 2, 1, 8, 5, 3, 4, 9, 7}
 * 
 * 第二次步长h=1,
 * 那么数组按照步长只有1个数组了
 * {6, 0, 2, 1, 8, 5, 3, 4, 9, 7}
 * 
 * 对这个数组进行一次插入排序后，最终顺序就成为：
 * {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
 */
public class ShellSort implements Sortable{

	@Override
	public void sort(int[] arr) {
		
		int stepLen = this.getStepLength(arr.length);

		while (stepLen >= 1) {
			for (int i = stepLen; i < arr.length; i++) {
				int j = i - stepLen;
				int get = arr[i];
				while (j >= 0 && arr[j] > get) {
					arr[j + stepLen] = arr[j];
					j = j - stepLen;
				}
				arr[j + stepLen] = get;
			}
			stepLen = (stepLen - 1) / 3; // 递减增量
		}

	}
	
	/**
	 * 步长的生成是关键
	 */
	private int getStepLength(int arrLength){
		int result = 0;
		
		while (result <= arrLength) {
			result = 3 * result + 1;
		}
		return result;
	}

}
