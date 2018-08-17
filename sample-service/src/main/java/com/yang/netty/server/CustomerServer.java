package com.yang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.client.InitializerTerminal;
import com.yang.netty.factory.initializer.CustomerChannelInitializerFactory;
import com.yang.netty.pojo.NettyConstant;

public class CustomerServer extends InitializerTerminal{
	
	protected static final Logger logger = LoggerFactory.getLogger(CustomerServer.class);
	
	public CustomerServer(){
		super(new CustomerChannelInitializerFactory());
	}
	
	public void bind() {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
	        ServerBootstrap bootstrap = new ServerBootstrap();
	        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
	                .option(ChannelOption.SO_BACKLOG, 100)
	                .handler(new LoggingHandler(LogLevel.INFO))
	                .childHandler(factory.serverInitializer());
	
	        // 绑定端口，同步等待成功
	        ChannelFuture channelFuture = bootstrap.bind(NettyConstant.PORT).sync();
	        
	        channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
    }

}
