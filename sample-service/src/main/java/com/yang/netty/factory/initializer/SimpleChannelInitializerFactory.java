package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.SimpleChannelInitializer;

public class SimpleChannelInitializerFactory extends ChannelInitializerFactory{

	@Override
	public ChannelHandler create(){
		return new SimpleChannelInitializer();
	}
}
