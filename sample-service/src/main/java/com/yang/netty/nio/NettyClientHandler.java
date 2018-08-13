package com.yang.netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class NettyClientHandler extends ChannelHandlerAdapter {

	private final ByteBuf buf ;
	
	public NettyClientHandler(){
		byte[] writeBytes = "QUERY TIME ORDER".getBytes();
		buf = Unpooled.buffer(writeBytes.length);
		buf.writeBytes(writeBytes);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf readBuf = (ByteBuf) msg;
		
		byte[] readBytes = new byte[readBuf.readableBytes()];
		readBuf.readBytes(readBytes);
		
		String body = new String(readBytes,"UTF-8");
System.out.println(body);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
}
