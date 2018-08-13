package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.DefaultClientInitializer;
import com.yang.netty.channel.initializer.DefaultServerInitializer;
import com.yang.netty.channel.initializer.SimpleClientInitializer;
import com.yang.netty.channel.initializer.SimpleServerInitializer;

public class SimpleChannelInitializerFactory extends ChannelInitializerFactory{

	public ChannelHandler clientInitializer(){
		return new SimpleClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new SimpleServerInitializer();
	}
}
