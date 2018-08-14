package com.yang.netty.nio.delimiterbased;

import com.yang.netty.client.NettyClient;
import com.yang.netty.factory.initializer.DelimiterChannelInitializerFactory;

/**
 * 使用DelimiterBasedFrameDecoder解码器解决粘包与拆包问题
 * 但是使用自定义的分割符
 * @author yangyaming
 */
public class Client {

	public static void main(String[] args){
		new NettyClient(new DelimiterChannelInitializerFactory()).connect("127.0.0.1",8989);
	}
}
