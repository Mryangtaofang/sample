package com.yang.gof.state.demo;

public class MorningWorkState implements TimeState{

	@Override
	public void doSomething(Work work) {
		if(work.getTime() < 12){
			System.out.println("早上工作时间!");
			return;
		}
		
		work.setTimeState(new NoonState());
		work.doWork();
	}

}
