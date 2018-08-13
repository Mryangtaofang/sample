package com.yang.java.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import com.yang.java.aio.server.AsyncServer;



public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,AsyncServer>{

	@Override
	public void completed(AsynchronousSocketChannel socketChannel, AsyncServer asyncServer) {
		System.out.println("client is accepted");
		asyncServer.serverChannel.accept(asyncServer, this);
		ByteBuffer byteBuf = ByteBuffer.allocate(1024);
		System.out.println("read is listening");
		socketChannel.read(byteBuf,byteBuf,new ReadHandler(socketChannel));
	}

	@Override
	public void failed(Throwable exc, AsyncServer attachment) {
		attachment.serverChannel.accept(attachment, this);
	}



}
