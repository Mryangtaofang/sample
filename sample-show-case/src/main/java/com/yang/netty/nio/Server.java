package com.yang.netty.nio;

import com.yang.netty.server.NettyServer;

/**
 * 这里模拟了粘包与拆包问题
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new NettyServer().bind(8989);
	}
}
