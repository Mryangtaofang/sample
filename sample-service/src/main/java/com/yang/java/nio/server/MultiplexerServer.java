package com.yang.java.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.yang.java.nio.NIOHelper;

public class MultiplexerServer extends NIOHelper implements Runnable{

	private ServerSocketChannel serverChannel;
	
	private Selector selector;

	private volatile boolean stop = false;
	
	public MultiplexerServer(){
		try {
			System.out.println("Server init...");
			//打开ServerSocketChannel
			serverChannel = ServerSocketChannel.open(); 
			//初始化监听的端口和地址
			SocketAddress socketAddress = new InetSocketAddress(PORT);
			//绑定监听地址
			serverChannel.socket().bind(socketAddress,1024);
			//配置为非阻塞IO
			serverChannel.configureBlocking(false);
			//选择器初始化
			selector = Selector.open();
			//channel绑定选择器
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("Server started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (!stop) {
			try {
				doSelect(selector);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		closeSelector(selector);
	}


	@Override
	public void handleInput(SelectionKey key) throws IOException {
		if(!key.isValid())
			return;
		
		if(key.isAcceptable()){
			System.out.println("key.isAcceptable.....");
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false);
			sc.register(selector, SelectionKey.OP_READ);
			return;
		}
		
		if(key.isReadable()){
			System.out.println("key.isReadable.....");
			
			String content = doRead(key);
			doWrite((SocketChannel)key.channel(), content);
			return;
		}
		
		if(key.isConnectable()){
			System.out.println("key.isConnectable.....");
			return;
		}
		
		if(key.isWritable()){
			System.out.println("key.isWritable.....");
			return;
		}
	}

}
