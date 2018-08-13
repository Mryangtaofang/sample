package com.yang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.client.NettyClientHandler;
import com.yang.netty.factory.initializer.ChannelInitializerFactory;

public class NettyServer {
	protected static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);
	
	private ChannelInitializerFactory factory;
	
	public NettyServer(){
		factory = new ChannelInitializerFactory();
	}
	
	public NettyServer(ChannelInitializerFactory factory){
		this.factory =  factory;
	}
	
	public void bind(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup,workerGroup)
				 .channel(NioServerSocketChannel.class)
				 .option(ChannelOption.SO_BACKLOG, 1024)
				 .childHandler(factory.create());
		
		logger.info("server start");
		
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			
		logger.info("server is listening");
		
			channelFuture.channel().closeFuture().sync();
			
		logger.info("server stop");
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	private  class ChildChannelHandler extends  ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel channel) throws Exception {
			channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
			channel.pipeline().addLast(new StringDecoder());
			channel.pipeline().addLast(new NettyServerHandler());
		}

	}
}
