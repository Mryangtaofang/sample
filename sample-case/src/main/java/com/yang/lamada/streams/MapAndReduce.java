package com.yang.lamada.streams;

import java.util.Arrays;
import java.util.List;

public class MapAndReduce {

	/*************************** 以前的方式  **************************************/
	List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

	public void applying() {
		for (Integer cost : costBeforeTax) {
			double price = 1.12 * cost;
			System.out.print(price + " ");
		}
	}
	
	public void total() {
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);
	}
	
	/*************************** 以前的方式 **************************************/

	
	/*************************** lamada表达式 **************************************/
	List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);

	public void applying2() {
		costBeforeTax2.stream().map((cost) -> 1.12 * cost)
				.forEach(System.out::print);
	}

	public void total2() {
		// reduce() 是将集合中所有值结合进一个，Reduce类似SQL语句中的sum(), avg() 或count()
		
		double bill = costBeforeTax2.stream()
				.map((cost) -> cost + .12 * cost)
				.reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
	}

	/*************************** lamada表达式 **************************************/
	
	public static void main(String[] args) {
		MapAndReduce mr = new MapAndReduce();
		mr.applying();
		mr.total();
		System.out.println("=========================================");
		mr.applying2();
		mr.total2();
	}
}
