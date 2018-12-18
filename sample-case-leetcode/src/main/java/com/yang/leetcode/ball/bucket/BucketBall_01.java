package com.yang.leetcode.ball.bucket;

import org.junit.Test;

/**
 * 将n个球放到m个盒子，每个球都是相同的，盒子也相同，请问有多少种方法？
 * 
 * 例子：比如4个球，3个盒子
 *
 * 第一个盒子	第二个盒子	    第三个盒子
 *   0         0       4
 *   0	       1       3
 *   0         2       2
 *   
 *   1         1       2
 *   
 * 放法：4
 *  004和040,400都是算同一种放法。
 */
public class BucketBall_01 {

	private int prev = 0;
	
	/**
	 * 递归解法1
	 */
	public int allCase(int n ,int m){
		if(n <= 0 || m <= 0)
			return 0;
		
		if(m == 1 || n == 1)
			return 1;
		
		int total = 0;
		for(int i=prev; i<n ;i++){
			prev = i;
			if((n-i) >= i)
				total += allCase(n-i,m-1);
		}
		
		return total;
	}
	
	/**
	 * 
	 */
	public int allCase2(int n ,int m){
		if(m == 1 || n == 0) return 1;
		
		return (m > n) ? allCase2(n, n) : allCase2(n, m-1) + allCase2(n-m, m);
	}
	
	public int allCaseDP(int n ,int m){
		if(n <= 0 || m <= 0)
			return 0;
		
		if(m == 1 || n == 1)
			return 1;
		
		//dp[i][j] 表示在i个球放入j个盒子里
		int[][] dp = new int[n+1][m+1]; 
		
		for(int i=0; i<=n; i++){
			
			for(int j=1; j<=m; j++){
				if(i == 0 || i== 1 || j == 1){
					dp[i][j] = 1;
					continue;
				}
				
				dp[i][j] = dp[i][j-1];
		
				if(i>=j)
					dp[i][j] += dp[i-j][j];
			}
		}
		
		return dp[n][m];
	}
	
	@Test
	public void teseCase(){
		System.out.print(new BucketBall_01().allCase2(5, 3));
	}
}
