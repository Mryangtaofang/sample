package com.yang.redis.redisson.delayqueue.spring;

import java.util.UUID;

public class DelayJob {
	private String jobID;
	//延迟job的一些参数
	private JobParams jobParams;
	//处理器的名称
	private String handler;
	
	public DelayJob() {
		super();
		jobID = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public JobParams getJobParams() {
		return jobParams;
	}

	public void setJobParams(JobParams jobParams) {
		this.jobParams = jobParams;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	
}
