package com.yang.kafka.partitioner;


import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



/**
 * kafka消费者
 */
public class PartitionerConsumer {
	private  static Properties props = new Properties();
	
	private static boolean isClose = false;
	
	static{
	     props.put("bootstrap.servers", "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	}
	
	public  static void main(String args[]){
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList(PartitionerProducer.TOPIC_NAME));
	     while (!isClose) {
	         ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("partition = %d offset = %d, key = %s, value = %s%n",record.partition(), record.offset(), record.key(), record.value());
	     }
	     
	     consumer.close();
	}
}
