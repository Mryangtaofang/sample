package com.yang.kafka.partitioner;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

/**
 * 自定义分区器
 */
public class MyPartitioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> configs) {}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes,Object value, byte[] valueBytes, Cluster cluster) {
		if(key == null)
			return 0;
		
		List<PartitionInfo>  availablePartitions = cluster.availablePartitionsForTopic(topic);
		if(availablePartitions == null || availablePartitions.size() <= 0)
			return 0;
		
		int partitionKey = Integer.parseInt((String)key);
		
		int partitionSize = availablePartitions.size();
		
		return availablePartitions.get(partitionKey % partitionSize).partition();
	}

	@Override
	public void close() {}

}
