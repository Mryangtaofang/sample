package com.yang.kafka.partitioner;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * kafka生产者
 * 使用自定义的分片器发送消息
 */
public class PartitionerProducer {
	
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
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("partitioner.class", "com.yang.kafka.partitioner.MyPartitioner");
	}
	
	public static void main(String[] args) {
		 Producer<String, String> producer = new KafkaProducer<>(props);
		 
		 for (int i = 0; i < 10; i++)
			 producer.send(new ProducerRecord<String, String>(TOPIC_NAME, Integer.toString(i),Integer.toString(i+3000)));
		 producer.close();
	}

}
