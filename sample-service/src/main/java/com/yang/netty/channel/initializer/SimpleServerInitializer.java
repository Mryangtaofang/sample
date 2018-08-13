package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import com.yang.netty.server.SimpleServerHandler;

/**
 * 换行分隔符解码器
 * 字符串解码器
 * @author yangyaming
 */
public class SimpleServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
		channel.pipeline().addLast(new StringDecoder());
		channel.pipeline().addLast(new SimpleServerHandler());
	}
	
}
