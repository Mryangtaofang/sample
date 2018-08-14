package com.yang.netty.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.pojo.SubscribeReq;
import com.yang.netty.pojo.SubscribeResp;

public class SubReqServerHandler extends ChannelHandlerAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(SubReqServerHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
logger.info("channel read .......");

		SubscribeReq req = (SubscribeReq)msg;
		
logger.info("读取到的消息为：" + req.toString()); 

		SubscribeResp resp =  getResp(req.getSubReqID());
		
logger.info("写出去数据为：" + resp.toString()); 

		ctx.writeAndFlush(resp);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("channel read complete");
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info("exception...........");
		ctx.close();
	}
	
	
	private SubscribeResp getResp(int subReqID){
		SubscribeResp resp = new SubscribeResp();
		resp.setSubReqID(subReqID);
		resp.setRespCode(0);
		resp.setDesc("Betty book order succeed");
		return resp;
	}
}
