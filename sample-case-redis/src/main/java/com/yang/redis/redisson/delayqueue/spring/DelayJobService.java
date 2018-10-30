package com.yang.redis.redisson.delayqueue.spring;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;

import com.yang.redis.redisson.config.RedissonConfig;

public class DelayJobService {

	public void submitJob(DelayJob job, Long delay, TimeUnit timeUnit) {
		RedissonClient client = RedissonConfig.getClient();
		RBlockingQueue blockingQueue = client.getBlockingQueue(JobTimer.jobsTag);
		
		RDelayedQueue delayedQueue = client.getDelayedQueue(blockingQueue);
		delayedQueue.offer(job, delay, timeUnit);
	}
}
