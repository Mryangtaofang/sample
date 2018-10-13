package com.yang.leetcode.array;

import org.junit.Test;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 *	You are giving candies to these children subjected to the following requirements:
 *	
 *	Each child must have at least one candy.
 *	Children with a higher rating get more candies than their neighbors.
 *	What is the minimum candies you must give?
 *
 *	
 *  
 */
public class Candy4Child {
	/**
	 *	解法：
	 *	用一个int[] candies数组，记录每个孩子所得到的糖果数，
	 */
	public int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0)
			return 0;

		int[] candies = new int[ratings.length];
		candies[0] = 1;
		for (int i = 1; i < ratings.length; i++) {
			//如果这个孩子的值比前一个的大，他的糖果数应该更多
			if (ratings[i] > ratings[i - 1])
				candies[i] = candies[i - 1] + 1;
			else if(ratings[i] < ratings[i - 1])
				candies[i] = Math.min(candies[i - 1] -1, 1);
			else 
				candies[i] = 1;

			if (candies[i] == 0) { //如果碰到糖果数为0，把他调整为1，并且重新调整之前的糖果数
				candies[i] = 1;
				for (int j = i-1; j >= 0; j--) {
					 if(ratings[j] > ratings[j+1]){
						 if(j>0 && ratings[j] > ratings[j-1])
							 candies[j]= Math.max(candies[j+1] +1,candies[j]);
						 else
							 candies[j] = candies[j+1] +1;
					 }else if (ratings[j] < ratings[j+1])
                        break;
				}
			}
		}

		int allCandies = 0;
		for (int candy : candies){
			System.out.println(candy);
			allCandies += candy;
		}

		return allCandies;
	}
	
	@Test 
	public void testCase() {
		new Candy4Child().candy(new int[]{1,3,4,3,2,1});
	}
}
