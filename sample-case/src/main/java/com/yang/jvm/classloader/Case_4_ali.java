package com.yang.jvm.classloader;

public class Case_4_ali {

	static {
		System.out.println("static");
	}

	private static void drive() {
		System.out.println("fast");
	}

	public static void main(String[] args) {
		drive();
		drive();
	}
}
