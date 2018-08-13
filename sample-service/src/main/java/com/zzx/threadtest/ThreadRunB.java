package com.zzx.threadtest;

public class ThreadRunB implements Runnable{

	@Override
	public void run() {
		ThreadTest.thread_b();
	}

}
