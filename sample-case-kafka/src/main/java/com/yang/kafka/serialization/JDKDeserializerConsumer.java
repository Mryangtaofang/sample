package com.yang.kafka.serialization;


import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.yang.kafka.User;

/**
 * 消费者-使用jdk反序列化
 */
public class JDKDeserializerConsumer {
	private  static Properties props = new Properties();
	
	private static boolean isClose = false;
	
	static{
	     props.put("bootstrap.servers", "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "com.yang.kafka.serialization.JDKDeserializer");
	}
	
	public  static void main(String args[]){
	     KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList(JSONSerializerProducer.TOPIC_NAME));
	     while (!isClose) {
	         ConsumerRecords<String, User> records = consumer.poll(Duration.ofMillis(100));
	         for (ConsumerRecord<String, User> record : records)
	             System.out.printf("key = %s, value = %s%n", record.key(), record.value());
	     }
	     
	     consumer.close();
	}
}
