package com.yang.netty.nio;


public class Client {

	public static void main(String[] args){
		new NettyClient().connect("127.0.0.1",8989);
	}
}
