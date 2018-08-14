package com.yang.protobuf;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yang.netty.protobuf.SubscribeReqProto;

public class TestSubScribeReqProto {

	public static byte[] encode(SubscribeReqProto.SubscribeReq req){
		return req.toByteArray();
	}
	
	public static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException{
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	
	
	public static SubscribeReqProto.SubscribeReq createSubscribeReq() {
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("yangyaming");
		builder.setProductName("苹果");
		
		List<String> address = new ArrayList<String>();
		address.add("Nanjing");
		address.add("Wuhan");
		builder.addAllAddress(address);
		return builder.build();
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException{
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.print("Before encode:" + req.toString());
		SubscribeReqProto.SubscribeReq newReq = decode(encode(req));
		System.out.print("After encode:" + newReq.toString());
		
		System.out.print("After Before equal:" + newReq.equals(req));
	}
}
