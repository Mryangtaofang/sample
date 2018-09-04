package com.yang.netty.nio.delimiterbased;

import com.yang.netty.factory.initializer.DelimiterChannelInitializerFactory;
import com.yang.netty.server.NettyServer;

/**
 * 使用DelimiterBasedFrameDecoder解码器解决粘包与拆包问题
 * 但是使用自定义的分割符
 * @author yangyaming
 */
public class Server {

	public static void main(String[] args){
		new NettyServer(new DelimiterChannelInitializerFactory()).bind(8989);
	}
}
