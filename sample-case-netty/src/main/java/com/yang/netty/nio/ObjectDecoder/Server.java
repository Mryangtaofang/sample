package com.yang.netty.nio.ObjectDecoder;

import com.yang.netty.factory.initializer.SubReqChannelInitializerFactory;
import com.yang.netty.server.NettyServer;

/**
 * 使用ObjectDecoder解码器
 * 使用的是jdk的序列化方式
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new NettyServer(new SubReqChannelInitializerFactory()).bind(8989);
	}
}
