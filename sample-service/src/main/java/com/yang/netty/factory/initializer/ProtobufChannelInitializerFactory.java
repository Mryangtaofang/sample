package com.yang.netty.factory.initializer;

import io.netty.channel.ChannelHandler;

import com.yang.netty.channel.initializer.ProtobufClientInitializer;
import com.yang.netty.channel.initializer.ProtobufServerInitializer;

public class ProtobufChannelInitializerFactory extends ChannelInitializerFactory{

	public ChannelHandler clientInitializer(){
		return new ProtobufClientInitializer();
	}
	
	public ChannelHandler serverInitializer(){
		return new ProtobufServerInitializer();
	}
}
