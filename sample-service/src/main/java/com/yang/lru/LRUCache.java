package com.yang.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	private LinkedHashMap<Integer, Integer> map;
	private final int maxCapacity;

	public LRUCache(int capacity) {
		this.maxCapacity = capacity;
		
		map = new LinkedHashMap<Integer, Integer>(maxCapacity, 0.75f, true) {

			private static final long serialVersionUID = 1696190114153598669L;

			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > maxCapacity;
			}
		};
	}

	public int get(int key) {
		return map.getOrDefault(key, -1);
	}

	public void set(int key, int value) {
		map.put(key, value);
	}
}
