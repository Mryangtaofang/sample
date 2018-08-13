package com.yang.netty.factory.initializer;

import com.yang.netty.channel.initializer.DefaultChannelInitializer;

import io.netty.channel.ChannelHandler;

public class ChannelInitializerFactory {

	public ChannelHandler create(){
		return new DefaultChannelInitializer();
	}
}
