package com.yang.java.aio;

import com.yang.java.aio.server.AsyncServer;



public class AIOServer {

	public static void main(String[] args){
		new Thread(new AsyncServer()).start();
	}
	
}
