package com.yang.netty.factory.handler;

import io.netty.channel.ChannelHandler;

import com.yang.netty.server.NettyServerHandler;

public class SimpleHanlderFactory extends ChannelHandlerFactory{

	@Override
	public ChannelHandler serverHandler(){
		return new NettyServerHandler();
	}
	
	@Override
	public ChannelHandler clientHandler(){
		return new NettyServerHandler();
	}
}
