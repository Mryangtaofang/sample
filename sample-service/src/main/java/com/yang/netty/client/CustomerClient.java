package com.yang.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.yang.netty.factory.initializer.CustomerChannelInitializerFactory;
import com.yang.netty.pojo.NettyConstant;

public class CustomerClient extends InitializerTerminal{
	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	public CustomerClient(){
		super(new CustomerChannelInitializerFactory());
	}
	
	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) {
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class)
					 .option(ChannelOption.TCP_NODELAY, true)
			         .handler(factory.clientInitializer());

			// 发起异步连接操作
			ChannelFuture future = bootstrap.connect(	new InetSocketAddress(host, port),
														new InetSocketAddress(NettyConstant.LOCALIP,NettyConstant.LOCAL_PORT)).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();

		} finally {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);
						connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}
	}
}
