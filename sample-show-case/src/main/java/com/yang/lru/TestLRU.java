package com.yang.lru;

import java.util.Map;

import org.junit.Test;

public class TestLRU {

	@Test
	public void testLRU(){
		LRULinkHashMap<String,String> map = new LRULinkHashMap<String,String>(4);
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		printMap(map);
		System.out.println("##################");
		printMap(map);
		System.out.println("##################");
		map.get("3");
		map.get("3");
		map.get("2");
		map.put("5", "5");
		printMap(map);
	}
	
	public static void printMap(LRULinkHashMap<String,String> map){
		for(Map.Entry<String,String> entry: map.entrySet()){
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
