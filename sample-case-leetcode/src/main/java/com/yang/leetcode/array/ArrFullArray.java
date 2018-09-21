package com.yang.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * 计算数组的全排列,
 * 比如{1,2,3}
 * 打印
 * {1,2,3}
 * {2,1,3}
 * {3,1,2}
 * {3,2,1}
 * {1,3,2}
 * {2,3,1}
 */
public class ArrFullArray {
	public void fullArray(int inArr[]){
		get(inArr,0,inArr.length-1);
	}

	
	public void get(int inArr[],int start,int end){
		if(start >= end){
			System.out.println(Arrays.toString(inArr));
			return;
		}
		
		for (int i = start; i <= end; i++) {
			swap(inArr,i,start);
			get(inArr,start+1,end);
			swap(inArr,start,i);
		}
	}
	
	private void swap(int[] inArr, int i, int start) {
		if(i == start || i > inArr.length)
			return;
		int temp = inArr[i];
		inArr[i] = inArr[start];
		inArr[start] = temp;
	}
	
	private void swap(char[] inArr, int i, int start) {
		if(i == start || i > inArr.length)
			return;
		char temp = inArr[i];
		inArr[i] = inArr[start];
		inArr[start] = temp;
	}
	
    public ArrayList<String> Permutation(String str) {
    	ArrayList<String> result = new ArrayList<String>();
    	if(str == null || "".equals(str))
    		return result;
    	
    	int length = str.length();
    	char[] chasrArr = str.toCharArray();
    	
    	getAllArr(chasrArr,0,length-1,result);
    	Collections.sort(result);
        return result;
    }


	private void getAllArr(char[] chasrArr, int start, int end, ArrayList<String> result) {
		if(start >= end ){
			result.add(String.valueOf(chasrArr));
			return;
		}
		
		for(int i = start;i <= end;i++){
			if(i != start && chasrArr[i] == chasrArr[start])
				continue;
			swap(chasrArr,start,i);
			getAllArr(chasrArr,start+1,end,result);
			swap(chasrArr,start,i);
		}
	}


	@Test
	public void testFullArray(){
//		new ArrFullArray().fullArray(new int[]{1,2,3,4,5,6,7});
		ArrayList<String> result = new ArrFullArray().Permutation("abc");
		System.out.println(result);
	}
}
