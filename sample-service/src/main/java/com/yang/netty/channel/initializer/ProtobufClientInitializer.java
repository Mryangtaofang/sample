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
 * 无解码器的Handler
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
