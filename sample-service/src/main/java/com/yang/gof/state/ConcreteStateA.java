package com.yang.gof.state;

public class ConcreteStateA implements State{

	@Override
	public void handle(Context context) {
		System.out.println("ConcreteStateA...................");
		context.setState(new ConcreteStateB());
	}

}
