package com.yang.java.aio;

import com.yang.java.aio.client.AsyncClient;

public class AIOClient {

	public static void main(String[] args){
		new Thread(new AsyncClient()).start();;
	}
}
