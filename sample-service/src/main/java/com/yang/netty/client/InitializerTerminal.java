package com.yang.netty.client;

import com.yang.netty.factory.initializer.ChannelInitializerFactory;

public class InitializerTerminal {
	protected ChannelInitializerFactory factory;

	public InitializerTerminal() {
		factory = new ChannelInitializerFactory();
	}

	public InitializerTerminal(ChannelInitializerFactory factory) {
		this.factory = factory;
	}
	
}
