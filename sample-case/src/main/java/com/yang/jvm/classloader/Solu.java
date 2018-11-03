package com.yang.jvm.classloader;

public class Solu {

	public static void main(String[] participants) {
		
	}
	
	public int reverse(int x) {
		if (x == 0)
			return 0;

		long result = 0;
		while (x != 0) {
			int pop = x % 10;
			result = result * 10 + pop;

			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
				return 0;
			x = x / 10;
		}

		return (int) result;
	}
}
