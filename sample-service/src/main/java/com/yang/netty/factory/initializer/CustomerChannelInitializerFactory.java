package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.SubReqClientInitializer;
import com.yang.netty.channel.initializer.SubReqServerInitializer;

public class CustomerChannelInitializerFactory extends ChannelInitializerFactory{

	public ChannelHandler clientInitializer(){
		return new SubReqClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new SubReqServerInitializer();
	}
}
