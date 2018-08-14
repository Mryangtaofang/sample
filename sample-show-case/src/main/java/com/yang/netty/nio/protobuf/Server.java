package com.yang.netty.nio.protobuf;

import com.yang.netty.factory.initializer.ProtobufChannelInitializerFactory;
import com.yang.netty.server.NettyServer;

/**
 * 使用Protobuf相关的编解码器
 * 使用的是Protobuf的序列化方式
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new NettyServer(new ProtobufChannelInitializerFactory()).bind(8989);
	}
}
