package com.yang.kafka.serialization;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.yang.kafka.User;

/**
 * 生产者-使用jdk序列化
 */
public class JDKSerializerProducer {

	public static final String TOPIC_NAME = "producer-0"; 
	private  static Properties props = new Properties();
	
	static{
		 props.put("bootstrap.servers", "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "com.yang.kafka.serialization.JDKSerializer");
	}
	
	public static void main(String[] args) {
		 Producer<String, User> producer = new KafkaProducer<>(props);
		 
		 User user = new User(101L,"kafka","serializer@kafka.com",1);
		 producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user.getId()), user));
		 
		 producer.close();
	}
}
