package com.yang.gof.state;

import com.yang.gof.state.demo.Work;

/**
 * 状态模式
 * 对于状态模式的理解
 * 在状态模式中最核心的是一个state的接口,
 * @author yangyaming
 */
public class WorkStateMain {

	public static void main(String[] args) {
		new Work(7).doWork();
		new Work(8).doWork();
		
		new Work(10).doWork();
		
		new Work(13).doWork();
	}

}
