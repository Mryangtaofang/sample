package com.yang.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadExecutors {
	
	public void testExecutors(){
		/**
		 *	public static ExecutorService newCachedThreadPool() {
		 *  	return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, 
		 *  									TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
		 *	}
		 *	此线程池刚创建的时候，还没有任务，则没有创建线程
		 *	SynchronousQueue,没有缓存,如果来了一任务，找一个空闲的线程，如果找不到，就去创建新的线程，如果超过了最大值，抛出异常
		 */
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		/**
		 *  return new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,
         *                          new LinkedBlockingQueue<Runnable>());
		 */
		executorService = Executors.newFixedThreadPool(4);
		
		
		executorService = Executors.newSingleThreadExecutor();
		
		
		executorService = Executors.newScheduledThreadPool(4);
		
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
	}
}
