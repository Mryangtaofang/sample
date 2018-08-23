package com.yang.gof.state.demo;

public class Work {
	
	private TimeState timeState;
	
	private Integer time;
	
	public Work(Integer time) {
		this.timeState = new MorningState();
		this.time = time;
	}
	
	public TimeState getTimeState() {
		return timeState;
	}

	public void setTimeState(TimeState timeState) {
		this.timeState = timeState;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	public void doWork(){
		timeState.doSomething(this);
	}

	
}
