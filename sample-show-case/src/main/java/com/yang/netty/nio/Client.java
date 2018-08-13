package com.yang.netty.nio;

import com.yang.netty.client.NettyClient;


public class Client {

	public static void main(String[] args){
		new NettyClient().connect("127.0.0.1",8989);
	}
}
