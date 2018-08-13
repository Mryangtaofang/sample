package com.yang.netty.nio.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	public void bind(int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup,workerGroup)
				 .channel(NioServerSocketChannel.class)
				 .option(ChannelOption.SO_BACKLOG, 1024)
				 .childHandler(new ChildChannelHandler());
			System.out.println("server start");
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
System.out.println("server is listening");
			channelFuture.channel().closeFuture().sync();
			System.out.println("server stop");
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
			channel.pipeline().addLast(new NettyServerHandler());
		}

	}
}
