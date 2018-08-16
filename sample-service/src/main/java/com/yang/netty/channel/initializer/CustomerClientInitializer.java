package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import com.yang.netty.decoder.NettyMessageDecoder;
import com.yang.netty.encoder.NettyMessageEncoder;
import com.yang.netty.server.handler.HeartBeatReqHandler;
import com.yang.netty.server.handler.LoginAuthReqHandler;

/**
 * 无解码器的Handler
 * @author yangyaming
 */
public class CustomerClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
		ch.pipeline().addLast("MessageEncoder",new NettyMessageEncoder());
		ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
		ch.pipeline().addLast("LoginAuthHandler",new LoginAuthReqHandler());
		ch.pipeline().addLast("HeartBeatHandler",new HeartBeatReqHandler());
	}
}
