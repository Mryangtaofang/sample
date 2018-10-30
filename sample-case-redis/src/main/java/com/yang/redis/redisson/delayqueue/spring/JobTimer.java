package com.yang.redis.redisson.delayqueue.spring;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;

import com.yang.utils.ThreadPoolUtils;

public class JobTimer {

	static final String jobsTag = "customer_jobtimer_jobs";

	private RedissonClient client;

	private ApplicationContext context;

	public void startJobTimer() {
		final RBlockingQueue<DelayJob> blockingQueue = client.getBlockingQueue(jobsTag);
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						DelayJob job = blockingQueue.take();
						ThreadPoolUtils.execute(new ExecutorTask(context, job));
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

	class ExecutorTask implements Runnable {

		private ApplicationContext context;

		private DelayJob delayJob;

		public ExecutorTask(ApplicationContext context, DelayJob delayJob) {
			this.context = context;
			this.delayJob = delayJob;
		}

		@Override
		public void run() {
			ExecuteJob service = (ExecuteJob) context.getBean(delayJob.getHandler());
			service.execute(delayJob);
		}
	}
}
