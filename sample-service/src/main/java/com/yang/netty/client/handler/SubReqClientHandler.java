package com.yang.netty.client.handler;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.pojo.SubscribeReq;

public class SubReqClientHandler extends ChannelHandlerAdapter {
	protected static final Logger logger = LoggerFactory.getLogger(SubReqClientHandler.class);
	
	public SubReqClientHandler(){}

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
	
	
	private SubscribeReq getReq(int id){
		SubscribeReq req = new SubscribeReq();
		req.setAddress("湖北省武汉市东湖高新技术开发区光谷大道61号（近三环） 光谷智慧园 26栋");
		req.setPhoneNumber("15927011158");
		req.setProductName("苹果");
		req.setUserName("yangyaming");
		req.setSubReqID(id);
		return req;
	}
}
