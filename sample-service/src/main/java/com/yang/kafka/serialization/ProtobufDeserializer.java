package com.yang.kafka.serialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.yang.kafka.protobuf.service.Protobufable;

public class ProtobufDeserializer implements Deserializer<Protobufable>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public Protobufable deserialize(String topic, byte[] data) {
		return null;
	}

	@Override
	public void close() {

	}
}
