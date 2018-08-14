package com.yang.netty.nio.linebased;

import com.yang.netty.client.NettyClient;
import com.yang.netty.factory.initializer.SimpleChannelInitializerFactory;

/**
 * 使用LineBasedFrameDecoder解码器解决粘包与拆包问题
 * 但是这必须约定消息是以'/n'或者是'/r/n'来分割的
 * @author yangyaming
 */
public class Client {

	public static void main(String[] args){
		new NettyClient(new SimpleChannelInitializerFactory()).connect("127.0.0.1",8989);
	}
}
