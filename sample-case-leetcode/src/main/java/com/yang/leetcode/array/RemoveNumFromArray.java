package com.yang.leetcode.array;

import org.junit.Test;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length
 *
 */
public class RemoveNumFromArray {

	public int removeElement(int[] array, int num) {
		int len = array.length;
		for (int i = 0; i < len; i++) {
			if (array[i] == num) {
				while (len > i && array[--len] == num);
				array[i] = array[len];
			}
		}
		return len;
	}
	
	@Test
	public void testCase(){
		new RemoveNumFromArray().removeElement(new int[]{3,3,1,4,3,5},3);
	}
}
