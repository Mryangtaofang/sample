package com.yang.kafka.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.yang.kafka.protobuf.service.Protobufable;

/**
 * protobuf序列化方式实现kafka消息的的序列化
 */
public class ProtobufSerializer implements Serializer<Protobufable>{
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}
	
	@Override
	public byte[] serialize(String topic, Protobufable data) {
		return data.encode();
	}

	@Override
	public void close() {}
}
