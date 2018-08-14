package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.DelimiterBaseClientInitializer;
import com.yang.netty.channel.initializer.DelimiterBaseServerInitializer;

public class DelimiterChannelInitializerFactory extends ChannelInitializerFactory{

	public ChannelHandler clientInitializer(){
		return new DelimiterBaseClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new DelimiterBaseServerInitializer();
	}
}
