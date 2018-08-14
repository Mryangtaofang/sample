package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import com.yang.netty.client.handler.SubReqClientHandler;

/**
 * 无解码器的Handler
 * @author yangyaming
 */
public class SubReqClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new ObjectDecoder(1024,ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
		channel.pipeline().addLast(new ObjectEncoder());
		channel.pipeline().addLast(new SubReqClientHandler());
	}
}
