package com.yang.gof.state.demo;

public class MorningState implements TimeState{

	@Override
	public void doSomething(Work work) {
		if(work.getTime() < 8){
			System.out.println("吃早餐!");
			return;
		}
		
		work.setTimeState(new MorningWorkState());
		work.doWork();
	}

}
