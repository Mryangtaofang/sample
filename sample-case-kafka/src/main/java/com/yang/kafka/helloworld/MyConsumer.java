package com.yang.kafka.helloworld;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;



/**
 * kafka消费者
 */
public class MyConsumer {
	
	public  static void main(String args[]){
	     Properties props = new Properties();
	     /** kafka集群ip **/
	     props.put("bootstrap.servers", "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092");
	     
	     /** 由于kafka有消费组的概念,每个消费者要制定一个group **/
	     props.put("group.id", "test");
	     
	     /** 我们这里使用kafka high level模式，自动提交offset**/
	     props.put("enable.auto.commit", "true");
	     
	     /** 自动提交的时间间隔 **/
	     props.put("auto.commit.interval.ms", "1000");
	     
	     /** 制定key合value的序列化方式,这里制定的是kafka的字符串序列化 **/
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList("mr-yang"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
	     }
	}
}
