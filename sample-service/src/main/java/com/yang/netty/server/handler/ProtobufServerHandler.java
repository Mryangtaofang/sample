package com.yang.netty.server.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.netty.protobuf.SubscribeReqProto;
import com.yang.netty.protobuf.SubscribeRespProto;

public class ProtobufServerHandler extends ChannelHandlerAdapter{

	protected static final Logger logger = LoggerFactory.getLogger(ProtobufServerHandler.class);
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
logger.info("channel read .......");

	SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
		
logger.info("读取到的消息为：" + req.toString()); 

	SubscribeRespProto.SubscribeResp resp =  getResp(req.getSubReqID());
		
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
	
	
	private SubscribeRespProto.SubscribeResp getResp(int subReqID){
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
		builder.setSubReqID(subReqID);
		builder.setDesc("Netty book order succeed");
		builder.setRespCode(101);
		return builder.build();
	}
}
