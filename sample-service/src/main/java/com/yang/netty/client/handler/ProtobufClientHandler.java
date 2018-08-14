package com.yang.netty.client.handler;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.protobuf.SubscribeReqProto;

public class ProtobufClientHandler extends ChannelHandlerAdapter {
	protected static final Logger logger = LoggerFactory.getLogger(ProtobufClientHandler.class);
	
	public ProtobufClientHandler(){}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelActive.................."); 
		
		for(int i=0;i< 10;i++ ){
			ctx.writeAndFlush(getReq(i));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead.................."); 
		logger.info("读取到的消息为：" + msg); 
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("ChannelHandle 异常！");
		ctx.close();
	}
	
	
	private SubscribeReqProto.SubscribeReq getReq(int id){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(id);
		builder.setUserName("yangyaming");
		builder.setProductName("苹果");
		
		List<String> address = new ArrayList<String>();
		address.add("湖北省武汉市东湖高新技术开发区光谷大道61号（近三环） 光谷智慧园 26栋");
		address.add("Wuhan");
		builder.addAllAddress(address);
		return builder.build();
	}
}
