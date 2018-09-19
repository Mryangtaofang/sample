package com.yang.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWDictionary {
	private final Map<String, String> m = new TreeMap<String, String>();
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock readLock = readWriteLock.readLock();
	private final Lock writeLock = readWriteLock.writeLock();

	/**
	 * 这里get 和  allKeys 可以同时执行
	 */
	public String get(String key) {
		readLock.lock();
		try {
			return m.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public String[] allKeys() {
		readLock.lock();
		try {
			return (String[])m.keySet().toArray();
		} finally {
			readLock.unlock();
		}
	}

	public String put(String key, String value) {
		writeLock.lock();
		try {
			return m.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	public void clear() {
		writeLock.lock();
		try {
			m.clear();
		} finally {
			writeLock.unlock();
		}
	}
}
