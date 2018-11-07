package com.yang.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 其实就Timer来讲就是一个调度器
 *
 */
public class TestTimer {

	public static void main(String[] args){
		
		Timer timer = new Timer();
		
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("11232");
			}
		}, 3000, 1000);
	}
}
