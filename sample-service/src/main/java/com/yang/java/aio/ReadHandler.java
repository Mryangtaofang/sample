package com.yang.java.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

public class ReadHandler implements CompletionHandler<Integer , ByteBuffer>{

	private AsynchronousSocketChannel socketChannel;
	
	public ReadHandler(AsynchronousSocketChannel sc){
		this.socketChannel = sc;
	}

	@Override
	public void completed(Integer result, ByteBuffer byteBuffer) {
		System.out.println("read had done!");
		byteBuffer.flip();
		byte[] body = new byte[byteBuffer.remaining()];
		byteBuffer.get(body);
		
		try {
			String data = new String(body,"utf-8");
			System.out.println("接收到的数据为：" + data);
			String currentTime = "QUERY TIME ORDER".equals(data) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
			doWrite(currentTime);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private void doWrite(String data) {
		if(socketChannel == null || data == null)
			return;
		
		byte[] bytes = data.getBytes();
		ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
		writeBuf.put(bytes);
		writeBuf.flip();
		System.out.println("write is listending!");
		socketChannel.write(writeBuf, writeBuf, new CompletionHandler<Integer , ByteBuffer>(){

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				System.out.println("write has done!");
				//如果没有发送完，继续发送
				if(buffer.hasRemaining())
					socketChannel.write(buffer, buffer, this);
				
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
