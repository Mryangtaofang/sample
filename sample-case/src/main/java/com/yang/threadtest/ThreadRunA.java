package com.yang.threadtest;

public class ThreadRunA implements Runnable{

	@Override
	public void run() {
		ThreadTest.thread_a();
	}

}
