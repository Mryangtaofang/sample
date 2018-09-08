package com.yang.kafka.producer;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

/**
 * 将消息发送到指定的partition中
 * @author yangyaming
 */
public class PartitionProducer {
	
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
	}
	
	public static void main(String[] args) {
		 Producer<String, String> producer = new KafkaProducer<>(props);
		 
		 //获取该topic的所有partition信息
		 List<PartitionInfo> partitions = producer.partitionsFor(TOPIC_NAME);
		 printAllPartition(partitions);
		 
		 /**将消息发送分片列表的到第二个partition中**/
		 int sendPartition = partitions.get(1).partition();
		 
		 System.out.println("消息将发送到partition：" + sendPartition + "中.");
		 
		 for (int i = 2000; i < 2100; i++)
			 producer.send(new ProducerRecord<String, String>(TOPIC_NAME, Integer.toString(i)));
		 System.out.println("发送完成");
		 producer.close();
	}

	//打印所有的partition
	private static void printAllPartition(List<PartitionInfo> partitions) {
		if(partitions == null || partitions.size() <= 0)
			return;
		
		System.out.println("topic:" + TOPIC_NAME + ",所有的partition如下:");
		
		partitions.forEach((partition) -> System.out.println("partition:" + partition.partition()));
	}

}
