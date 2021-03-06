package com.yang.lru;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * LinkedHashMap其本质是一个没有锁的简单lru实现
 * http://flychao88.iteye.com/blog/1977653
 */
public class LRULinkHashMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 673176306721763406L;

	private final int maxCapacity;

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	private final Lock lock = new ReentrantLock();

	public LRULinkHashMap(int maxCapacity) {
		super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
		this.maxCapacity = maxCapacity;
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size() > maxCapacity;
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	@Override
	public V get(Object key) {
		try {
			lock.lock();
			return super.get(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V put(K key, V value) {
		try {
			lock.lock();
			return super.put(key, value);
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		try {
			lock.lock();
			return super.size();
		} finally {
			lock.unlock();
		}
	}

	public void clear() {
		try {
			lock.lock();
			super.clear();
		} finally {
			lock.unlock();
		}
	}

	public Collection<Map.Entry<K, V>> getAll() {
		try {
			lock.lock();
			return new ArrayList<Map.Entry<K, V>>(super.entrySet());
		} finally {
			lock.unlock();
		}
	}

}
