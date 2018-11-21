package com.yang.num;

public class SwapNum {

	public static void main(String[] args){
		System.out.println(new SwapNum().swapNum(1,2)[1]);
	}
	
	private int[] swapNum(int a,int b){
		b = a^b;
		a = a^b;
		b = a^b;
		
		return new int[]{a,b};
	}
}
