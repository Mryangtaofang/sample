package com.yang.threadtest;

public class ThreadRunC implements Runnable{
	public ThreadTest test;
	
	public ThreadRunC(ThreadTest test) {
		this.test = test;
	}

	@Override
	public void run() {
		test.thread_C();
	}

}
