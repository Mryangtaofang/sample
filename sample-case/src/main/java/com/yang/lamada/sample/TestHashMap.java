package com.yang.lamada.sample;

import java.util.HashMap;

import org.junit.Test;

public class TestHashMap {

	public void testHashMap(){
		HashMap<Integer,Integer> s = new HashMap<>(4,1);
		s.put(1, 2);
		s.put(3, 4);
		System.out.println(s);
		s.put(5, 6);
	}
	
	@Test
	public void testCase(){
		new TestHashMap().testHashMap();
	}
}
