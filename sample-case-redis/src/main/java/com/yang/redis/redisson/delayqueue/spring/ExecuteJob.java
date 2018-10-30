package com.yang.redis.redisson.delayqueue.spring;

public interface ExecuteJob {

	public void execute(DelayJob job);
}
