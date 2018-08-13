package com.yang.netty.factory.initializer;

import com.yang.netty.channel.initializer.DefaultClientInitializer;
import com.yang.netty.channel.initializer.DefaultServerInitializer;

import io.netty.channel.ChannelHandler;

public class ChannelInitializerFactory {

	public ChannelHandler clientInitializer(){
		return new DefaultClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new DefaultServerInitializer();
	}
}
