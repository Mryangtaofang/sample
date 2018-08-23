package com.yang.gof.state;

public class Context {

	private State state;
	
	public void request(){
		state.handle(this);
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
