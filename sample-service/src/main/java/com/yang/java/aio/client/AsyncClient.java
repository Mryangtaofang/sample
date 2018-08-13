package com.yang.java.aio.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import com.yang.java.aio.server.AsyncServer;

public class AsyncClient implements Runnable ,CompletionHandler<Void,AsyncClient>{
	
	private AsynchronousSocketChannel socketChannel;
	
	private CountDownLatch latch;
	
	public AsyncClient(){
		try {
			System.out.println("AsynchronousSocketChannel is initing!");
			socketChannel = AsynchronousSocketChannel.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		System.out.println("AsynchronousSocketChannel is connecting!");
		socketChannel.connect(new InetSocketAddress("127.0.0.1", AsyncServer.PORT), this, this);
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void completed(Void result, AsyncClient attachment) {
		System.out.println("AsynchronousSocketChannel is connected!");
		byte[] bytes = "QUERY TIME ORDER".getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
		writeBuf.put(bytes);
		writeBuf.flip();
		System.out.println("write start!");
		socketChannel.write(writeBuf, writeBuf,new CompletionHandler<Integer,ByteBuffer>(){

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				System.out.println("write success!");
				if(buffer.hasRemaining()){
					socketChannel.write(buffer, buffer, this);
					return;
				}
				
				ByteBuffer readBuf = ByteBuffer.allocate(1024);
				socketChannel.read(readBuf, readBuf, new CompletionHandler<Integer,ByteBuffer>(){

					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						attachment.flip();
						byte[] bytes = new byte[attachment.remaining()];
						attachment.get(bytes);
						String body = null;
						try {
							body = new String(bytes,"utf-8");
							System.out.print("read data:" + body);
							latch.countDown();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						close();
					}
				});
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				close();
			}
		});
	}


	@Override
	public void failed(Throwable exc, AsyncClient attachment) {
		close();
	}

	
	
	private void close(){
		try {
			if(socketChannel != null)
				socketChannel.close();
			
			latch.countDown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
