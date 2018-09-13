package com.yang.kafka.transaction;


import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.kafka.User;
import com.yang.kafka.bulider.KafkaTransactionBuilder;

/**
 * 事物测试消费者
 */
public class TransactionConsumer {
	protected static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);
	private static boolean isClose = false;

	
	public  static void main(String args[]){
		KafkaTransactionBuilder<String, User, byte[]> builder = new KafkaTransactionBuilder<String, User, byte[]>();
	    Consumer<String, byte[]> consumer = builder.buildConsumer();
	    
	    consumer.subscribe(Arrays.asList(ConsumeTransformProduce.TOPIC_NAME));
	    try{
		    while (!isClose) {
		        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
		        for (ConsumerRecord<String, byte[]> record : records)
		        	System.out.printf("key = %s,offset=%s partition=%s value = %s%n", record.key(),record.offset(),record.partition(), new User(record.value()));
		        
		        //手动提交
		        consumer.commitAsync();
		    }	
		    
	    }catch(Exception e){
	    	logger.error("kafka消息消费异常！",e);
	    }
	    consumer.close();
	}
}
