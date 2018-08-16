package com.yang.netty.channel.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import com.yang.netty.client.handler.ProtobufClientHandler;
import com.yang.netty.protobuf.SubscribeRespProto;

/**
 * ProtobufDecoder仅仅只能解码，但是不能处理半包的问题，
 * 所以在ProtobufDecoder之前有个ProtobufVarint32FrameDecoder
 * ProtobufVarint32FrameDecoder可以处理半包消息,
 * 如果你注释掉这个解码器，运行程序会出错
 * 当然你也可以继承ByteToMessageDecoder,自己处理半包消息
 * @author yangyaming
 */
public class ProtobufClientInitializer extends ChannelInitializer<SocketChannel>{

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
		channel.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
		channel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
		channel.pipeline().addLast(new ProtobufEncoder());
		channel.pipeline().addLast(new ProtobufClientHandler());
	}
}
