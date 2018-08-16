package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import com.yang.netty.decoder.NettyMessageDecoder;
import com.yang.netty.encoder.NettyMessageEncoder;
import com.yang.netty.server.handler.HeartBeatRespHandler;
import com.yang.netty.server.handler.LoginAuthRespHandler;

/**
 * 自定义协议的编解码器
 * @author yangyaming
 */
public class CustomerServerInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
        ch.pipeline().addLast(new NettyMessageEncoder());
        ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
        ch.pipeline().addLast(new LoginAuthRespHandler());
        ch.pipeline().addLast("HeartBeatHandler",new HeartBeatRespHandler());
	}
}
