package com.yang.kafka.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public class JDKDeserializer implements Deserializer<Serializable>{

	private ByteArrayInputStream byteArrStream;
    private ObjectInputStream objectStream;
    
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	@Override
	public Serializable deserialize(String topic, byte[] data) {
		try {
			byteArrStream = new ByteArrayInputStream(data);
			objectStream = new ObjectInputStream(byteArrStream);
			
			return (Serializable)objectStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
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
