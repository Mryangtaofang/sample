package com.yang.kafka.bulider;

import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class KafkaBuilder<T,P,C> {
	protected static final String servers = "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092";
	
	protected static final String default_serializer = "org.apache.kafka.common.serialization.StringSerializer";
	protected static final String default_deserializer = "org.apache.kafka.common.serialization.StringDeserializer";
	
	protected static final int default_buffer_size = 33554432;
	protected static final int default_batch_size = 16384;
	protected static final String default_group_id = "default-group";
	
	public Producer<T,P> buildProducer(){
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", default_batch_size);
		props.put("linger.ms", 1);
		props.put("buffer.memory", default_buffer_size);
		props.put("key.serializer", default_serializer);
		props.put("value.serializer", default_serializer);
		return new KafkaProducer<>(props);
	}
	
	public Consumer<T,C> buildConsumer(){
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
	    props.put("group.id", default_group_id);
	    props.put("enable.auto.commit", "true");
	    props.put("auto.commit.interval.ms", "1000");
	    props.put("key.deserializer", default_deserializer);
	    props.put("value.deserializer", default_deserializer);
		return new KafkaConsumer<>(props);
	}

}
