package com.yang.callable.future;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFuture {
	public static class MyCallable implements Callable<Boolean> {
        private int flag = 0;
        public MyCallable(int flag) {
            this.flag = flag;
        }

        @Override
        public Boolean call() throws Exception {
            if (this.flag == 0) {
            	Thread.sleep(2000);
                return true;
            }
            else if (this.flag == 1) {
                try {
                    while (true) {
                        System.out.println("looping");
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                return false;
            }
            else {
                throw new Exception("Bad flag value!");
            }
        }

    }

    public static void main(String[] args) {

        MyCallable task0 = new MyCallable(0);
        MyCallable task1 = new MyCallable(1);
        MyCallable task2 = new MyCallable(2);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            Future<Boolean> future0 = executor.submit(task0);
            System.out.println("task0: " + future0.get());

            Future<Boolean> future1 = executor.submit(task1);
            Thread.sleep(5000);
            System.out.println("task1 cancelled:" + future1.cancel(true)); // 注意这时候Task在Sleep，是被Interrupted了

            Future<Boolean> future2 = executor.submit(task2);
            System.out.println("task2: " + future2.get());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.println("-------------------------------------");

    }
}
