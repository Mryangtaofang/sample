package com.yang.bit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.alibaba.fastjson.JSON;

public class Case {
	
	private static final int base_50 = 300; 
	private static final int sacle_400 = 2000; 
	
	private static final int base_100 = 1000; 
	private static final int sacle_600 = 2000; 
	
	private static final int base_400 = 3000; 
	private static final int sacle_2000 = 4000; 
	
	private static final int sacle_900 = 1500; 
	
	public static void main(String[] args){
		Random r = new Random();
		double[] strs = new double[12];
		
		String[] result = new String[12];
		int len = strs.length;
		for(int i=0; i<len; i++){
			strs[i] = r.nextDouble();
			if(strs[i]>0.5)
				strs[i] = -(strs[i]-0.5);
			
			strs[i] = strs[i]*0.04;
			result[i] = new BigDecimal(strs[i]).setScale(8,RoundingMode.HALF_UP).toPlainString();
//			strs[i] =  new BigDecimal(strs[i]).setScale(8,RoundingMode.HALF_UP).doubleValue();
			
		}

		System.out.println(JSON.toJSONString(result).replaceAll("\"", "")); 
	}
	
	
	public static void randomCase1(){
		Random r = new Random();
		
		int[] strs = new int[31*10*8];
		int len = strs.length;
		for(int i=0; i<len; i++)
			strs[i] = r.nextInt(80) + 20;
	
		System.out.println(JSON.toJSONString(strs)); 
	}
	
	public static void randomCase(){
		Random r = new Random();

		int[] strs = new int[365];
		
		int len = strs.length;
	
		for(int i=0; i<len; i++){
			if(i < 50)
				strs[i] = r.nextInt(sacle_400) + base_50;
			
			else if(i < 125)
				strs[i] = r.nextInt(sacle_600) + base_100;
			
			else if( i < 175)
				strs[i] = r.nextInt(sacle_400) + base_50;
			
			else if(i < 250)
				strs[i] = r.nextInt(sacle_600) + base_100;
			
			else if(i < 321)
				strs[i] = r.nextInt(sacle_2000) + base_400;
			
			else
				strs[i] = r.nextInt(sacle_900) + base_100;
		}
		
		System.out.println(JSON.toJSONString(strs)); 
	}
}
