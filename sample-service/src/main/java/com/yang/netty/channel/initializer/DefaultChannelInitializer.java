package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import com.yang.netty.client.NettyClientHandler;

/**
 * 无解码器的Handler
 * @author yangyaming
 */
public class DefaultChannelInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new NettyClientHandler());
	}
}
