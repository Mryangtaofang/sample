package com.zzx.threadtest;

public class ThreadRunA implements Runnable{

	@Override
	public void run() {
		ThreadTest.thread_a();
	}

}
