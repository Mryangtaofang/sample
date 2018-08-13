package com.yang.netty.nio.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler extends ChannelHandlerAdapter{
	
	protected static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channel read .......");
		ByteBuf buf = (ByteBuf)msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		String body = new String(bytes,"UTF-8");
System.out.println(body);
		String currentTime = "QUERY TIME ORDER".equals(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
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
