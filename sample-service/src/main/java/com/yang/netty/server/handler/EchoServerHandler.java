package com.yang.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServerHandler extends ChannelHandlerAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
	
	private  static final String separator = "$_";
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
logger.info("channel read .......");

		String body = (String)msg;
		
logger.info("读取到的消息为：" + body); 

		body += separator;
logger.info("写出去数据为：" + body); 

		ByteBuf writeBuf = Unpooled.copiedBuffer(body.getBytes());
		ctx.writeAndFlush(writeBuf);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("channel read complete");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info("exception...........");
		ctx.close();
	}
}
