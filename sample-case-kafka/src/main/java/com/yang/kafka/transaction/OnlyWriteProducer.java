package com.yang.kafka.transaction;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yang.kafka.User;
import com.yang.kafka.bulider.KafkaTransactionBuilder;

/**
 * 多次生成消息类型的事物
 */
public class OnlyWriteProducer {
	protected static final Logger logger = LoggerFactory.getLogger(OnlyWriteProducer.class);
	public static final String TOPIC_NAME = "producer-0"; 
	
	public static void main(String[] args) {
		 Producer<String, User> producer = new KafkaTransactionBuilder<String, User, byte[]>().buildProducer("producer-0-transaction");
		 //初始化事物
		 producer.initTransactions();
		 //开始事物
		 producer.beginTransaction();
		 
		 try{
			 User user = new User(105L,"kafka",null,1);
			 producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user.getId()), user));
			 
			 User user2 = new User(106L,"netty",null,0);
			 producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user2.getId()), user2));
			 //提交事物
			 producer.commitTransaction();
		 }catch(Exception e){
			 logger.error("kafka消息发送异常！",e);
			 //停止事物
			 producer.abortTransaction();
		 }
		 
		 producer.close();
	}
}
