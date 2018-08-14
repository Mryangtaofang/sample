package com.yang.netty.nio.protobuf;

import com.yang.netty.client.NettyClient;
import com.yang.netty.factory.initializer.ProtobufChannelInitializerFactory;

/**
 * 使用Protobuf相关的编解码器
 * 使用的是Protobuf的序列化方式
 * @author yangyaming
 */
public class Client {

	public static void main(String[] args){
		new NettyClient(new ProtobufChannelInitializerFactory()).connect("127.0.0.1",8989);
	}
}
