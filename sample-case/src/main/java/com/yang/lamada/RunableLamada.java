package com.yang.lamada;

/**
 * 实现线程时，可以使用lamada表达式
 * 
 * 同时匿名类也可以使用
 * @author yangyaming
 */
public class RunableLamada {
	
	public static void main(String[] args){
		//以前起一个线程需要这么写
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("这是一个子线程 1！");
			}
		}).start();;

		//现在可以这么写
		new Thread(() -> {System.out.println("这是一个子线程 2！");}).start();
		
		//由于线程中只有一行代码，还可以精简为
		new Thread(() -> System.out.println("这是一个子线程 3！")).start();
		
		
		// 在使用匿名内部类，可以如下这么写  
		Runnable race1 = new Runnable() {  
		    @Override  
		    public void run() {  
		        System.out.println("Hello world !");  
		    }  
		};  
		  
		// 使用 lambda expression，可以这么写  
		Runnable race2 = () -> System.out.println("Hello world !");  
		   
		new Thread(race1).start();
		new Thread(race2).start();
	}
	
}
