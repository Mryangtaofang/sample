package com.yang.kafka.helloworld;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * kafka生产者
 * 这是一个简单的例子程序，将0-99发送到kafka中，然后消费端可以接受到消息，进行消费
 * @author yangyaming
 */
public class MyProducer {
	
	public static void main(String args[]){
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "192.168.1.3:9092,192.168.1.128:9092,192.168.1.130:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		 Producer<String, String> producer = new KafkaProducer<>(props);
		 for (int i = 0; i < 100; i++){
			 /**
			  * ProducerRecord 参数解析
			  * 第一个：mr-yang为生产者 topic名称,
			  * 第二个：对于生产者kafka2.0需要你指定一个key,在企业应用中，我们一般会把他当做businessId来用，比如订单ID,用户ID等等。
			  * 第三个：消息的主要信息
			  */
			 producer.send(new ProducerRecord<String, String>("mr-yang", Integer.toString(i), Integer.toString(i)));
		 }
		     

		 producer.close();
	}
}
