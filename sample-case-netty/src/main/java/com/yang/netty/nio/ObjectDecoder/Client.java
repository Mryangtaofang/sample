package com.yang.netty.nio.ObjectDecoder;

import com.yang.netty.client.NettyClient;
import com.yang.netty.factory.initializer.SubReqChannelInitializerFactory;

/**
 * 使用ObjectDecoder解码器
 * 使用的是jdk的序列化方式
 * @author yangyaming
 */
public class Client {

	public static void main(String[] args){
		new NettyClient(new SubReqChannelInitializerFactory()).connect("127.0.0.1",8989);
	}
}
