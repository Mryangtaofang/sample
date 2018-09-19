package com.yang.thread;

import java.lang.Thread.State;

public class ThreadMain {

	public static void main(String[] args) {
		Thread run_a = new Thread(new ThreadRunA());
		Thread run_b = new Thread(new ThreadRunB());
		Thread run_c = new Thread(new ThreadRunC(new ThreadTest()));
		System.out.println("主线程:线程运行开始.....");
		sleep(1000);
		
		run_a.start();
		sleep(1000);
		State state_a = run_a.getState();
		System.out.println("主线程:state_a-" + state_a.toString());
		
		run_b.start();
		sleep(1000);
		State state_b = run_b.getState();
		System.out.println("主线程:state_b-" + state_b.toString());
		
		run_c.start();
		sleep(1000);
		State state_c = run_c.getState();
		System.out.println("主线程:state_c-" + state_c.toString());
		
		try {
			System.out.println("主线程:等待_run_a join.....");
			run_a.join();
			System.out.println("主线程:等待_run_b join.....");
			run_b.join();
			System.out.println("主线程:等待_run_c join.....");
			run_c.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
