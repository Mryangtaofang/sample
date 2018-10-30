package com.yang.redis.redisson.delayqueue;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;

public class DelayJobService {

	private RedissonClient client;
	
	public DelayJobService(RedissonClient client) {
		super();
		this.client = client;
	}

	public void submitJob(DelayJob job, Long delay, TimeUnit timeUnit) {
		
		RBlockingQueue<DelayJob> blockingQueue = client.getBlockingQueue(JobTimer.jobsTag);
		RDelayedQueue<DelayJob> delayedQueue = client.getDelayedQueue(blockingQueue);
		
		delayedQueue.offer(job, delay, timeUnit);
	}
}
