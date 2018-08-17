package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.CustomerClientInitializer;
import com.yang.netty.channel.initializer.CustomerServerInitializer;

public class CustomerChannelInitializerFactory extends ChannelInitializerFactory{

	public ChannelHandler clientInitializer(){
		return new CustomerClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new CustomerServerInitializer();
	}
}
