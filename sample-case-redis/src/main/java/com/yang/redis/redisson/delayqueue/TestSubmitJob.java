package com.yang.redis.redisson.delayqueue;

import java.util.concurrent.TimeUnit;

import com.yang.redis.redisson.config.RedissonConfig;

public class TestSubmitJob {
	
	public static void main(String[] args){
		DelayJobService service = new DelayJobService(RedissonConfig.getClient());
		DelayJob job = new DelayJob();
		job.setParams("this is a job");
		service.submitJob(job, 30L, TimeUnit.SECONDS);
	}
}
