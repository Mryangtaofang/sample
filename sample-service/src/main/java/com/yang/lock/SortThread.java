package com.yang.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SortThread implements Callable<String>{

	private Condition waitCondition ;
	
	private Condition signalCondition ;
	
	private ReentrantLock lock;
	
	private String result ;
	
	private volatile Boolean  isStop = false; 
	
	public SortThread(Condition waitCondition,Condition signalCondition,String result,ReentrantLock lock) {
		this.waitCondition = waitCondition;
		this.signalCondition = signalCondition;
		this.result = result;
		this.lock = lock;
	}


	@Override
	public String call() throws Exception {
		while(!isStop){
			lock.lock();
			
			waitCondition.await();
			
			if(!isStop)
				System.out.print(" "+result);
			
			if(signalCondition != null)
				signalCondition.signal();
			
			lock.unlock();
		}

		return result;
	}
	
	public void stop(){
		lock.lock();
			isStop = true;
			waitCondition.signal();
		lock.unlock();
	}

}
