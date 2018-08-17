package com.yang.netty.nio.protocol;

import com.yang.netty.server.CustomerServer;

/**
 * 自定义协议
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new CustomerServer().bind();
	}
}
