package com.yang.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 *
 * 例子：nums=[1,2,3];
 * [
 * 					-- subsets     	start=0; 			arrayList=[]
 * 	[],             -- track 第一层     	start=0; 	i=0;	arrayList=[1]
 * 	[1],			-- track 第二层     	start=1; 	i=1;	arrayList=[1,2]
 * 	[1,2],         	-- track 第三层     	start=2; 	i=2;	arrayList=[1,2,3]
 * 	[1,2,3]			-- track 第四层     	start=3; 	i=3;	arrayList=[1,2,3]
 * 	[1,3]			-- track 第三层    	start=2; 	i=3;	arrayList=[1,2]
 * 	[2]				-- track 第二层     	start=1; 	i=2;	arrayList=[1]
 * 	[2,3]
 * 	[3]
 * ]
 */
public class SubSets {

	 public List<List<Integer>> subsets(int[] nums) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 
		 track(result,new ArrayList<Integer>(),nums,0);
		 return result;
	 }

	private void track(List<List<Integer>> result,ArrayList<Integer> arrayList, int[] nums, int start) {
		System.out.println("-------------- 第" + (start+1)+ "层 start --------------");
		System.out.println("result:" + result);
		System.out.println("arrayList:" + arrayList);
		System.out.println("start:" + start);
		result.add(new ArrayList<Integer>(arrayList));

		System.out.println("result添加 " + arrayList+" 元素");
		System.out.println("新的result:" + result);
		
		for (int i = start; i < nums.length; i++) {
			arrayList.add(nums[i]);
			System.out.println("arrayList添加 " + nums[i]+" 元素,新的arrayList:"+arrayList);
			track(result, arrayList, nums, i + 1);
			System.out.println("递归完之后的i="+i+",arrayList:"+arrayList);
			arrayList.remove(arrayList.size() - 1);
			System.out.println("arrayList移除最后一个元素,新的arrayList:"+arrayList);
		}
		System.out.println("-------------- 第" + (start+1)+ "层 end--------------");
	}
	
	@Test
	public void testCase(){
		new SubSets().subsets(new int[]{1,2,3});
	}
}
