package com.yang.netty.factory.handler;

import io.netty.channel.ChannelHandler;

import com.yang.netty.client.SimpleClientHandler;
import com.yang.netty.server.SimpleServerHandler;

public class SimpleHanlderFactory extends ChannelHandlerFactory{

	@Override
	public ChannelHandler serverHandler(){
		return new SimpleServerHandler();
	}
	
	@Override
	public ChannelHandler clientHandler(){
		return new SimpleClientHandler();
	}
}
