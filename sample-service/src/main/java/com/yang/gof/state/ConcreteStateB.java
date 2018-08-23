package com.yang.gof.state;

public class ConcreteStateB implements State{

	@Override
	public void handle(Context context) {
		System.out.println("ConcreteStateB...................");
		context.setState(new ConcreteStateA());
	}

}
