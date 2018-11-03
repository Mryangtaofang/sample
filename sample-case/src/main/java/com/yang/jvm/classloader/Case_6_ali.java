package com.yang.jvm.classloader;

public class Case_6_ali {

	public static void main(String[] args) {
		int x = 5;
		Case_6_ali p = new Case_6_ali();
		p.doStuff(x);
		System.out.print(" main x = " + x);

	}

	void doStuff(int x) {
		System.out.println(" doStuff x = " + x++);
	}
}
