package com.zzx.threadtest;

import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

	private final Lock lock = new ReentrantLock();
	
	private Stack<String> stack = new Stack<String>();
	
	public void add(String str){
		lock.lock();
		try {
			stack.add(str);
		} catch (Exception e) {
			lock.unlock();
		}
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
	}
	
	@Test
	public void testHashSet(){
		HashSet<String> set = new HashSet<String>();
		boolean isSuccess = set.add("yang");
		System.out.println(isSuccess);
		isSuccess = set.add("yang"); 
		System.out.println(isSuccess);
	}
	
	
}
