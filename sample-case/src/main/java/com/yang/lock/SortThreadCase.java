package com.yang.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.yang.utils.ThreadPoolUtils;

/***
 * 三个线程循环顺序打印打印A B C的问题
 * 
 * 1把锁，3个condition
 * 互相等待和唤醒
 * 
 * @author yangyaming
 *
 */
public class SortThreadCase {
	
	public static void main(String[] args){
		
		ReentrantLock lock = new ReentrantLock();
		
		Condition aCondition = lock.newCondition();
		Condition bCondition = lock.newCondition();
		Condition cCondition = lock.newCondition();
		
		SortThread threadA = new SortThread(aCondition, bCondition, "A",lock);
		SortThread threadB = new SortThread(bCondition, cCondition, "B",lock);
		SortThread threadC = new SortThread(cCondition, aCondition, "C\n",lock);
		
		ExecutorService executor = ThreadPoolUtils.getExecutorService();
		Future<String> resultA = executor.submit(threadA);
		Future<String> resultB = executor.submit(threadB);
		Future<String> resultC = executor.submit(threadC);
		
		sleep(3000L);
		
		lock.lock();
			System.out.println("开始");
			System.out.println("");
			aCondition.signal();
		lock.unlock();
		
		sleep(500L);
		
		
//		threadA.stop();
//		threadB.stop();
//		threadC.stop();
		
		sleep(3000L);
		
		if(resultA.isDone())
			System.out.println("Thread:A is stoped");
		
		if(resultB.isDone())
			System.out.println("Thread:B is stoped");
		
		if(resultC.isDone())
			System.out.println("Thread:C is stoped");
		
		System.out.println("关闭线程池");
		
		try {
			executor.shutdown();
			if(!executor.awaitTermination(200L, TimeUnit.MILLISECONDS))
				executor.shutdownNow();
			
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}
	
	private static void sleep(long  millies){
		try {
			Thread.sleep(millies);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
