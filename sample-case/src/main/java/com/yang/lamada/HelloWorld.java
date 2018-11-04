package com.yang.lamada;

import java.util.Arrays;
import java.util.List;

public class HelloWorld {

	public static List<String> players;
	
	static{
		players = Arrays.asList(new String[]{
			"Rafael Nadal", 
			"Novak Djokovic",  
			"Stanislas Wawrinka",  
			"David Ferrer",
			"Roger Federer",  
			"Andy Murray",
			"Tomas Berdych",  
			"Juan Martin Del Potro"
		});
	}
	
	public static void main(String[] args){

		// 以前的循环方式
		for (String player : players) 
			System.out.println(player);
		
		  
		// 使用 lambda 表达式以及函数操作(functional operation)  
		players.forEach((player) -> System.out.println(player)); 
		
		//在 Java 8 中使用双冒号操作符(double colon operator)  
		players.forEach(System.out::println); 
	}
	
}
