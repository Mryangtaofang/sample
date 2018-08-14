package com.yang.netty.nio;

import com.yang.netty.client.NettyClient;

/**
 * 这里模拟了粘包与拆包问题
 * @author yangyaming
 */
public class Client {

	public static void main(String[] args){
		new NettyClient().connect("127.0.0.1",8989);
	}
}
