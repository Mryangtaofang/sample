package com.yang.lamada;

import java.util.Arrays;
import java.util.Comparator;

/***
 * 函数式接口：Functional Interface. 
 * 定义的一个接口，接口里面必须 有且只有一个抽象方法 ，这样的接口就成为函数式接口。 
 * 
 * 比如Runable接口，里面只有一个run方法，那么他就是函数式接口
 * 
 * @author yangyaming
 */
public class SortLamada {

	public static String[] players;
	
	static{
		players = new String[]{
			"Rafael Nadal", 
			"Novak Djokovic",  
			"Stanislas Wawrinka",  
			"David Ferrer",
			"Roger Federer",  
			"Andy Murray",
			"Tomas Berdych",  
			"Juan Martin Del Potro"
		};
	}
	
	public static void main(String[] args){

		//使用匿名内部类根据 name 排序 players
		Arrays.sort(players, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return (s1.compareTo(s2));
			}
		});
		
		//使用lamada表达式,其实这还是匿名内部类的使用
		Arrays.sort(players,(s1,s2) -> s1.compareTo(s2));
	}
}
