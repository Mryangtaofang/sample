package com.yang.netty.nio.protocol;

import com.yang.netty.client.CustomerClient;

/**
 * 自定义的私有协议
 */
public class Client {

	public static void main(String[] args){
		new CustomerClient().connect("120.0.0.1",8989);
	}
}
