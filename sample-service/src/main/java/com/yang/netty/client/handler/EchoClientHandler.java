package com.yang.netty.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {

	protected static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);
	
	private int counter = 0;
	
	private static final String SEND_STRING = "Hello netty!$_";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelActive.................."); 
		
		ByteBuf buf = null;
		for(int i=0;i< 10;i++ ){
			buf = Unpooled.copiedBuffer(SEND_STRING.getBytes());
			ctx.writeAndFlush(buf);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead.................."); 
		
		String body = (String)msg;
		++counter;
		logger.info("读取到的消息为：" + body); 
		logger.info("counter：" + counter); 
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("ChannelHandle 异常！");
		ctx.close();
	}
}
