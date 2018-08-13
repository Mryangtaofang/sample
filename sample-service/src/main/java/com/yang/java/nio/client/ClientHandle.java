package com.yang.java.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.yang.java.nio.NIOHelper;

public class ClientHandle extends NIOHelper  implements Runnable{
	
	private Selector selector;
	
	private volatile boolean stop = false;
	
	private SocketChannel socketChannel;
	
	public ClientHandle(){
		try {
			System.out.println("client init...");
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//连接服务端
		doConnect();
		
		while(!stop){
			try {
				doSelect(selector);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		closeSelector(selector);
	}
	
	/**
	 * 开始建立连接
	 * connectSuccess = true, 表示TCP三次握手已经完成,连接建立成功，可以直接写数据了.
	 * connectSuccess = false,并不表示连接失败，TCP三次握手可能还没有完成。
	 */
	private void doConnect() {
		try {
			boolean connectSuccess = socketChannel.connect(new InetSocketAddress(HOST, PORT));
			if(connectSuccess){
				socketChannel.register(selector, SelectionKey.OP_READ);
				doWrite(socketChannel,"QUERY TIME ORDER");
			}else{
				socketChannel.register(selector, SelectionKey.OP_CONNECT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handleInput(SelectionKey key) throws IOException {
		if(!key.isValid())
			return;
		
		SocketChannel socketChannel = (SocketChannel) key.channel();
		if(key.isConnectable()){
			System.out.println("key.isConnectable....");
			if(socketChannel.finishConnect()){
				socketChannel.register(selector, SelectionKey.OP_READ);
				doWrite(socketChannel,"QUERY TIME ORDER");
			}else{
				//这里应该考虑重连的情况
				System.out.println("connect fail...");
				System.exit(-1);
			}
			return;
		}
		
		if(key.isReadable()){
			System.out.println("key.isReadable....");
			doRead(key);
			System.out.println("client stop...");
			close(key);
			this.stop = true;
			return;
		}
		
		if(key.isWritable()){
			System.out.println("key.isWritable....");
			return;
		}
	}

}
