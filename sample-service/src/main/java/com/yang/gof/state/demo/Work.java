package com.yang.gof.state.demo;

public class Work {
	
	private TimeState timeState;
	
	private Integer time;
	
	public Work(Integer time) {
		this.timeState = new MorningState();
		this.time = time;
	}
	
	/**
	 * 核心方法
	 */
	public void doWork(){
		timeState.doSomething(this);
	}

	public void setTimeState(TimeState timeState) {
		this.timeState = timeState;
	}

	public Integer getTime() {
		return time;
	}
}
