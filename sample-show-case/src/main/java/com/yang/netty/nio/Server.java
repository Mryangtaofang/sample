package com.yang.netty.nio;

import com.yang.netty.server.NettyServer;


public class Server {

	public static void main(String[] args){
		new NettyServer().bind(8989);
	}
}
