package com.yang.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import com.yang.netty.factory.initializer.ChannelInitializerFactory;

public class NettyClient {
	
	private ChannelInitializerFactory factory;
	
	public NettyClient(){
		factory = new ChannelInitializerFactory();
	}
	
	public NettyClient(ChannelInitializerFactory factory){
		this.factory =  factory;
	}
	
	public void connect(String hostName,int port){
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
					 .channel(NioSocketChannel.class)
					 .option(ChannelOption.TCP_NODELAY, true)
					 .handler(factory.clientInitializer());
			
			ChannelFuture channelFuture = bootstrap.connect(hostName,port).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}
}
