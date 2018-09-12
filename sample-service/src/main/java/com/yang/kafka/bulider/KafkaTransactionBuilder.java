package com.yang.kafka.bulider;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class KafkaTransactionBuilder<T,P,C> extends KafkaBuilder<T,P,C>{
	
	/**
	 * 构建生产者
	 */
	@Override
	public KafkaProducer<T,P> buildProducer(){
		Properties props = new Properties();
	    props.put("bootstrap.servers", servers);
	    props.put("batch.size", default_batch_size);
	    props.put("buffer.memory", default_buffer_size);
	    
		props.put("key.serializer", default_serializer);
		props.put("value.serializer", "com.yang.kafka.serialization.ProtobufSerializer");
		// 设置事物ID
		props.put("transactional.id", "my-transaction");
		
		props.put("acks", "all");
		props.put("retries", 3);
		props.put("enable.idempotence", true);
		props.put("linger.ms", 1);
		return new KafkaProducer<>(props);
	}
	
	/**
	 * 构建消费者
	 */
	@Override
	public KafkaConsumer<T,C> buildConsumer(){
		Properties props = new Properties();
	    props.put("bootstrap.servers", servers);
	    props.put("group.id", "test-transaction");
	    props.put("key.deserializer", default_deserializer);
	    props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
	    
		// 设置隔离级别
		props.put("isolation.level", "read_committed");
		// 关闭自动提交
	    props.put("enable.auto.commit", false);
	    
		return new KafkaConsumer<>(props);
	}
}
