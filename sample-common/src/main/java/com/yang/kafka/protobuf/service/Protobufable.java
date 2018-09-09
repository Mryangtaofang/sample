package com.yang.kafka.protobuf.service;

public interface Protobufable {

	//将对象转为字节数组
	public byte[] encode();

}
