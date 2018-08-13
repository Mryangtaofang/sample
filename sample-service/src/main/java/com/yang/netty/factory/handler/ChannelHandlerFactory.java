package com.yang.netty.factory.handler;

import io.netty.channel.ChannelHandler;

import com.yang.netty.client.NettyClientHandler;
import com.yang.netty.server.NettyServerHandler;

public class ChannelHandlerFactory {

	public ChannelHandler serverHandler(){
		return new NettyServerHandler();
	}
	
	public ChannelHandler clientHandler(){
		return new NettyClientHandler();
	}
}
