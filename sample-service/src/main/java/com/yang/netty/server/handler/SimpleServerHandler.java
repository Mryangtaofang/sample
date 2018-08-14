package com.yang.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServerHandler extends ChannelHandlerAdapter{
	protected static final Logger logger = LoggerFactory.getLogger(SimpleServerHandler.class);
	
	private  static final String separator = System.getProperty("line.separator");
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
logger.info("channel read .......");
		String body = (String)msg;
logger.info("读取到的消息为：" + body); 

		String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
		currentTime = currentTime + separator;
		
logger.info("写出去数据为：" + currentTime); 

		ByteBuf writeBuf = Unpooled.copiedBuffer(currentTime.getBytes());
		ctx.write(writeBuf);
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
