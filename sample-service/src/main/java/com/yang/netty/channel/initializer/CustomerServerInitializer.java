package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.decoder.NettyMessageDecoder;
import com.yang.netty.encoder.NettyMessageEncoder;
import com.yang.netty.server.handler.HeartBeatRespHandler;
import com.yang.netty.server.handler.LoginAuthRespHandler;

/**
 * 自定义协议的编解码器
 * @author yangyaming
 */
public class CustomerServerInitializer extends ChannelInitializer<SocketChannel>{
	
	protected static final Logger logger = LoggerFactory.getLogger(CustomerServerInitializer.class);
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
        ch.pipeline().addLast(new NettyMessageEncoder());
        ch.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(50));
        ch.pipeline().addLast(new LoginAuthRespHandler());
        ch.pipeline().addLast("HeartBeatHandler",new HeartBeatRespHandler());
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("一个客户端已经连接！name:" + ctx.name());
	}
	
	
}
