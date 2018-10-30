package com.yang.thread.pool;

import com.yang.utils.ThreadPoolUtils;

public class ThreadPoolCase {

	public static void main(String[] args){
		ThreadPoolUtils.execute(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程池!");
			}
		});
		
	}
}
