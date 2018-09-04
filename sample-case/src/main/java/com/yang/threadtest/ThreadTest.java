package com.yang.threadtest;

public class ThreadTest {

	public static synchronized void thread_a(){
		System.out.println("线程A:进入方法A.....");
		try {
			System.out.println("线程A:A_睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("线程A:A_再睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程A:出方法A.....");
	}
	
	public static synchronized void thread_b(){
		System.out.println("线程B:进入方法b.....");
		try {
			System.out.println("线程B:b_睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("线程B:b_再睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程B:出方法B.....");
	}
	
	public  synchronized void thread_C(){
		System.out.println("线程C:进入方法C.....");
		try {
			System.out.println("线程C:C_睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("线程C:C_再睡一秒.....");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程C:出方法C.....");
	}
	
}
