package com.yang.java.aio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import com.yang.java.aio.AcceptHandler;

public class AsyncServer implements Runnable{
	public static final int PORT = 8989;
	
	private CountDownLatch latch;
	
	public AsynchronousServerSocketChannel serverChannel;
	
	public AsyncServer(){
		try {
			System.out.println("server is initing.....");
			serverChannel = AsynchronousServerSocketChannel.open();
			serverChannel.bind(new InetSocketAddress(PORT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		System.out.println("server is started");
		try {
			latch.await();
			System.out.println("server is stoped");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	private void doAccept() {
		serverChannel.accept(this,new AcceptHandler());		
	}

}
