package com.yang.redis.redisson.delayqueue;

import java.util.UUID;

public class DelayJob {
	private String jobID;
	
	private String params;
	
	public DelayJob() {
		super();
		jobID = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
}
