package com.yang.jvm.classloader;

public class Case_2 {
	private String baseName = "base";

	public Case_2() {
		callName();
	}

	public void callName() {
		System.out.println(baseName);
	}

	static class Sub extends Case_2 {
		private String baseName = "sub";

		public void callName() {
			System.out.println(baseName);
		}
	}

	public static void main(String[] args) {
		new Sub();
	}
}
