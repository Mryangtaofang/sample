package com.yang.thread;

public class ThreadRunB implements Runnable{

	@Override
	public void run() {
		ThreadTest.thread_b();
	}

}
