package com.yang.leetcode;

import org.junit.Test;

/**
 * 
 */
public class SellerStock {

	/**
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * If you were only permitted to complete at most one transaction 
	 * (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	 * 
	 * Example 1:
	 * Input: [7, 1, 5, 3, 6, 4]
	 * Output: 5
     * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
     * 
     * Example 2:
     * Input: [7, 6, 4, 3, 1]
	 * Output: 0
	 * In this case, no transaction is done, i.e. max profit = 0.
	 */
	public int getMaxForOne(int[] prices){
		if(prices == null || prices.length <= 1)
			return 0;
		int buy = prices[0];
		int sell = prices[0];
		
		int earnings = 0;
		for(int price : prices){
			//最小买入价格
			buy = Math.min(buy, price);
			
			//最大收益,同时得出最佳的卖出价格
			if((price-buy) > earnings){
				earnings = price-buy;
				sell = price;
			}
		}
		
		System.out.println("买入价格：" + buy);
		System.out.println("卖出价格：" + sell);
		System.out.println("最大收益：" + earnings);
		
		return earnings;
	}
	
	
	/**
	 * 如果可以做多次此做多交易
	 */
	public int getMaxForMore(int[] prices){
		if(prices == null || prices.length <= 1)
			return 0;
		int earnings = 0;
		//假设在第一天买入，第一天卖出
		int buy = prices[0];
		int sell = prices[0];
		for(int price : prices){
			
			//如果有更高的卖出价，在此价格下卖出
			if(price >= sell){
				sell = price;
				continue;
			}
			
			//如果没有更高的卖出价，说明之前的那笔交易,可以成交
			if(sell > buy){
				earnings += sell - buy;
				printTransaction(buy,sell);

				//之前的交易成家，在当前价买入，并假设在此价格卖出
				buy = price;
				sell = price;
				continue;
			}
			
			//如果有更低的买入价,在此价格买入，并假设在此价格卖出
			if(buy > price ){
				buy = price;
				sell = price;
			}
		}
		
		//处理最后一笔交易
		if(sell > buy){
			earnings += sell - buy;
			printTransaction(buy,sell);
		}
		return earnings;
	}
	
	
	
	
	/**
	 * 如果可以做多次此做多交易
	 */
	public int getMaxForTwo(int[] prices){
		if(prices == null || prices.length <= 1)
			return 0;
		int earnings = 0;
		//假设在第一天买入，第一天卖出
		int buy = prices[0];
		int sell = prices[0];
		
		int earningsOne = 0;
		int earningsTwo = 0;
		for(int price : prices){
			
			//如果有更高的卖出价，在此价格下卖出
			if(price >= sell){
				sell = price;
				continue;
			}
			
			//如果没有更高的卖出价，说明之前的那笔交易,可以成交
			if(sell > buy){
				earnings = sell - buy;
				if(earnings > Math.min(earningsOne, earningsTwo)){
					if(earningsTwo > earningsOne)
						earningsOne = earnings;
					else
						earningsTwo = earnings;
				}
				printTransaction(buy,sell);

				//之前的交易成家，在当前价买入，并假设在此价格卖出
				buy = price;
				sell = price;
				continue;
			}
			
			//如果有更低的买入价,在此价格买入，并假设在此价格卖出
			if(buy > price ){
				buy = price;
				sell = price;
			}
		}
		
		//处理最后一笔交易
		if(sell > buy){
			earnings = sell - buy;
			if(earnings > Math.min(earningsOne, earningsTwo)){
				if(earningsTwo > earningsOne)
					earningsOne = earnings;
				else
					earningsTwo = earnings;
			}
			printTransaction(buy,sell);
		}
		return earningsTwo + earningsOne;
	}
	
	/**
	 * 只允许做k次做多交易
	 */
	public int getMaxForK(int[] prices,int k){
		if(prices == null || prices.length <= 1)
			return 0;
		//假设在第一天买入，第一天卖出
		int buy = prices[0];
		int sell = prices[0];
		int tran[] = new int[k];
		
		int minIndex = 0;
		for(int price : prices){
			//如果有更高的卖出价，在此价格下卖出
			if(price >= sell){
				sell = price;
				continue;
			}
			
			//如果没有更高的卖出价，说明之前的那笔交易,可以成交
			if(sell > buy){
				int earnings = sell - buy;
				if(earnings > tran[minIndex])
					minIndex = putTran(tran,minIndex,earnings);
				
				printTransaction(buy,sell);

				//之前的交易成家，在当前价买入，并假设在此价格卖出
				buy = price;
				sell = price;
				continue;
			}
			
			//如果有更低的买入价,在此价格买入，并假设在此价格卖出
			if(buy > price ){
				buy = price;
				sell = price;
			}
		}
		
		//处理最后一笔交易
		if(sell > buy){
			int earnings = sell - buy;
			if(earnings > tran[minIndex])
				minIndex = putTran(tran,minIndex,earnings);
			printTransaction(buy,sell);
		}
		
		
		return this.sum(tran);
	}
	
	private int sum(int[] tran) {
		int sumTran = 0;
		for(int tranOne : tran){
			sumTran += tranOne;
		}
		return sumTran;
	}


	/**
	 * 最小收益的交易下标
	 */
	private int putTran(int[] tran, int minIndex, int earnings) {
		tran[minIndex] = earnings;
		int minTranIndex = 0;
		
		for(int i=1 ; i < tran.length; i++){
			if(tran[minTranIndex] > tran[i])
				minTranIndex = i;
		}
		return minTranIndex;
	}


	private void printTransaction(int buy,int sell){
		System.out.println("---------交易成交---------");
		System.out.println("买入价格：" + buy);
		System.out.println("卖出价格：" + sell);
		System.out.println("获得收益：" + (sell - buy));
		System.out.println("-----------------------\n");
	}

	
	public int getMaxFor(int[] prices){
		int buy = Integer.MIN_VALUE;
		int prev_buy;
		int sell = 0;
		int prev_sell = 0;

	    for (int price : prices) {
	        prev_buy = buy;
	        buy = Math.max(prev_sell - price, prev_buy);
	        prev_sell = sell;
	        sell = Math.max(prev_buy + price, prev_sell);
	    }
	    return sell;
	}
	
	@Test
	public void testGetMax(){
		int[] prices = new int[]{1,3,1, 5, 3, 9}; 
		
		int earnings = new SellerStock().getMaxFor(prices);
		System.out.println("总收益:"+earnings);
	}
	
}
