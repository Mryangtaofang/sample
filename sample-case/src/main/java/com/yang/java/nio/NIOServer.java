package com.yang.java.nio;

import com.yang.java.nio.server.MultiplexerServer;


public class NIOServer {

	public static void main(String[] args){
		new Thread(new MultiplexerServer()).start();;
	}
	
}
