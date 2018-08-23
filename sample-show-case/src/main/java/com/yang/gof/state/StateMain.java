package com.yang.gof.state;

/**
 * 状态模式
 * 对于状态模式的理解
 * 在状态模式中最核心的是一个state的接口,
 * @author yangyaming
 */
public class StateMain {

	public static void main(String[] args) {
		Context context = new Context();
		context.setState(new ConcreteStateA());
		
		context.request();
		context.request();
	}

}
