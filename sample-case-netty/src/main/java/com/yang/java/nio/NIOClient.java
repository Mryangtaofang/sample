package com.yang.java.nio;

import com.yang.java.nio.client.ClientHandle;

public class NIOClient {

	public static void main(String[] args){
		new Thread(new ClientHandle()).start();;
	}
}
