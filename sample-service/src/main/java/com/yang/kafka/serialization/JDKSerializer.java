package com.yang.kafka.serialization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

/**
 * JDK序列化方式实现kafka消息的的序列化
 */
public class JDKSerializer implements Serializer<Serializable>{
	
	private ByteArrayOutputStream byteArrStream;
	private ObjectOutputStream objectStream;
	
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		byteArrStream = new ByteArrayOutputStream();
	}
	
	@Override
	public byte[] serialize(String topic, Serializable data) {
		if (data == null)
			return null;

		byte[] bytes = null;
		try {
			objectStream = new ObjectOutputStream(byteArrStream);
			objectStream.writeObject(data);

			bytes = byteArrStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}


	@Override
	public void close() {
		try {
			if(byteArrStream != null) byteArrStream.close();
			
			if(objectStream != null) objectStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
