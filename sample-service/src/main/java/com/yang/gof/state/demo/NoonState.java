package com.yang.gof.state.demo;

public class NoonState implements TimeState{

	@Override
	public void doSomething(Work work) {
		if(work.getTime() < 13){
			System.out.println("吃午饭的时间!");
			return;
		}

		work.setTimeState(new AfternoonWorkState());
		work.doWork();
	}

}
