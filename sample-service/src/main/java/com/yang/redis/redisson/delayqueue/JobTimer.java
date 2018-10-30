package com.yang.redis.redisson.delayqueue;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;

import com.yang.redis.redisson.config.RedissonConfig;

public class JobTimer {

	static final String jobsTag = "customer_jobtimer_jobs";

	public void startJobTimer() {
		final RBlockingQueue<DelayJob> blockingQueue = RedissonConfig.getClient().getBlockingQueue(jobsTag);
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("获取job!");
						DelayJob job = blockingQueue.take();
						System.out.println("取到一个job!");
						//执行延迟任务
						System.out.println("JobID:"+job.getJobID()+"\n Params:"+job.getParams());
						System.out.println("job执行完成!");
					} catch (Exception e) {
						e.printStackTrace();
						try {
							TimeUnit.SECONDS.sleep(60);
						} catch (Exception ex) {
							
						}
					}
				}
			}
		}.start();
	}

}
