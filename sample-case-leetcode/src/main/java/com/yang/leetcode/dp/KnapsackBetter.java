package com.yang.leetcode.dp;


/**
 * 金典的01背包问题.
 * 有6件物品,物品编号，重量，价值如下:
 * 
 * 物品编号：a  b  c  d  e  f
 * 物品重量：0  1  3  2  6  2
 * 物品价值：0  2  5  3  10 4
 * 
 * 现在给你个承重为12的背包， 如何让背包里装入的物品具有最大的价值总和？
 *	
 * 	在com.yang.leetcode.dp.Knapsack中，给出了01背包问题的通解
 * 	时间复杂度为O(weight*n),空间复杂度也为O(weight*n)
 * 	现在需要将他的空间复杂度降低到O(weight),而时间复杂度不变
 */
public class KnapsackBetter {

	public int solution(int[] weight,int[] value,int bagWeight){
		int len = weight.length;
		
		//maxValue[i] 表示承重为i的最大价值
		int[] maxValue = new int[bagWeight+1];
		
		for(int i = 1; i < len; i++){
	        for(int j=bagWeight; j>0; j--)
	        	if(j < weight[i])
	        		maxValue[j] = maxValue[j]; //maxValue[j]表示i-1时，承重为j的总价值
	        	else
	        		maxValue[j] = Math.max(maxValue[j], maxValue[j-weight[i]] + value[i]);
		}
		
		return maxValue[bagWeight];
	}
	
	public static void main(String[] args){
		
		int[] weight = new int[]{0,1,3,2,6,2};
		int[] value = new int[]{0,2,5,3,10,4};
		int bagWeight = 12;
		
		System.out.println(new KnapsackBetter().solution(weight, value, bagWeight));
	}
}
