package com.yang.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在实际应用中，线程池的写法
 * @author yangyaming
 */
public class ThreadPoolUtils {

    //核心线程数
    private static final int CORE_POOL_SIZE = 5;

    //最大线程数
    private static final int MAX_POOL_ZISE = 200;

    //存活时间
    private static final Long KEEP_ALIVE_TIME = 60L;

    //登记式单例
    private static class ThreadPoolHolder {
        private static final ThreadPoolExecutor executor =  new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_ZISE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    }

    public static ExecutorService getExecutorService(){
        return ThreadPoolHolder.executor;
    }

    /**
     * 执行task
     */
    public static void execute(Runnable task){
        ThreadPoolHolder.executor.execute(getExcpThread(task));
    }

    /**
     * 执行task，带自定义的异常处理
     */
    public static void execute(Runnable runnable,Thread.UncaughtExceptionHandler excpHandler){
        Thread thread = getExcpThread(runnable);
        if(excpHandler != null)
            thread.setUncaughtExceptionHandler(excpHandler);

        ThreadPoolHolder.executor.execute(thread);
    }

    /**
     * 默认线程异常处理器
     */
    static class ThreadUncaughtException implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread t, Throwable e) {

        }
    }

    /**
     * 获取带有异常处理的线程
     */
    public static Thread getExcpThread(Runnable task){
        Thread taskThread = null;
        if(task instanceof Thread)
            taskThread = (Thread)task;
        else
            taskThread = new Thread(task);

        if(taskThread.getUncaughtExceptionHandler() == null)
            taskThread.setUncaughtExceptionHandler(new ThreadUncaughtException());
        return taskThread;
    }

}
