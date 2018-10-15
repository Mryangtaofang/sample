package com.yang.cas;

import java.util.concurrent.TimeUnit;

/**
 * 有意思的可见性问题
 * https://blog.csdn.net/renwotao2009/article/details/51361760
 */
public class TestVolatile {

	private static boolean is = true;

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (TestVolatile.is) {
					i++;
					System.out.println(i);
					// 会强制刷新主内存的变量值到线程栈?
//					synchronized (this) {}
					// println 是synchronized的,会强制刷新主内存的变量值到线程栈?
//					System.out.println("1");
					// sleep 会从新load主内存的值?
//					try {
//						TimeUnit.MICROSECONDS.sleep(1);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				is = false; // 设置is为false，使上面的线程结束while循环
			}
		}).start();
		
	}
}