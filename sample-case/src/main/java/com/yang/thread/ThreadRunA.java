package com.yang.thread;

public class ThreadRunA implements Runnable{

	@Override
	public void run() {
		ThreadTest.thread_a();
	}

}
