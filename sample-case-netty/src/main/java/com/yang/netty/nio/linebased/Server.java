package com.yang.netty.nio.linebased;

import com.yang.netty.factory.initializer.SimpleChannelInitializerFactory;
import com.yang.netty.server.NettyServer;

/**
 * 使用LineBasedFrameDecoder解码器解决粘包与拆包问题
 * 但是这必须约定消息是以'/n'或者是'/r/n'来分割的
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new NettyServer(new SimpleChannelInitializerFactory()).bind(8989);
	}
}
