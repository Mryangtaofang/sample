package com.yang.gof.state.demo;

public class AfternoonWorkState implements TimeState{

	@Override
	public void doSomething(Work work) {
		if(work.getTime() < 18){
			System.out.println("下午工作时间!");
			return;
		}
		
		System.out.println("休息时间!");
	}

}
