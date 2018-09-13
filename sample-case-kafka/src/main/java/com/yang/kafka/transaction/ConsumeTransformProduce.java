package com.yang.kafka.transaction;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.yang.kafka.User;
import com.yang.kafka.bulider.KafkaTransactionBuilder;

/***
 * 实现的功能：
 * 接收到topic:producer-0的消息之后，添加email,然后在发送到topic:producer-1
 * 
 * 操作：
 * 先启动TransactionConsumer
 * 再启动当前类
 * 最后启动 OnlyWriteProducer
 */
public class ConsumeTransformProduce {
	protected static final Logger logger = LoggerFactory.getLogger(ConsumeTransformProduce.class);
	
	public static final String TOPIC_NAME = "producer-1"; 
	public static final String GROUP_ID = "consume_transform_produce"; 
	
	private static boolean isClose = false;
	
	public static void main(String[] args) {
		KafkaTransactionBuilder<String, User, byte[]> builder = new KafkaTransactionBuilder<String, User, byte[]>();
	    Consumer<String, byte[]> consumer = builder.buildConsumer(GROUP_ID);
		Producer<String, User> producer = builder.buildProducer("producer-1-transaction");
		// 初始化事物
		producer.initTransactions();

		/** 订阅producer-0 **/
		consumer.subscribe(Arrays.asList(OnlyWriteProducer.TOPIC_NAME));
		 
		while (!isClose) {
			// 开始事物
			producer.beginTransaction();

			try {
				ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
				
				Map<TopicPartition, OffsetAndMetadata> commits = Maps.newHashMap();
				for (ConsumerRecord<String, byte[]> record : records){
					User user = new User(record.value());
					System.out.printf("key = %s,offset=%s partition=%s value = %s%n",record.key(), record.offset(), record.partition(),user);
					
                    commits.put(new TopicPartition(record.topic(), record.partition()),new OffsetAndMetadata(record.offset()));

                    user.setEmail("consume_transform_produce@kafka.com");
                    /** 发送producer-1 **/
                    producer.send(new ProducerRecord<String, User>(TOPIC_NAME, Long.toString(user.getId()), user));
				}
				// 提交Offset
				producer.sendOffsetsToTransaction(commits, GROUP_ID);
				// 提交事务
				producer.commitTransaction();
			} catch (Exception e) {
				logger.error("kafka消息发送异常！", e);
				// 停止事物
				producer.abortTransaction();
			}
		}
		 
		producer.close();
	}
}
