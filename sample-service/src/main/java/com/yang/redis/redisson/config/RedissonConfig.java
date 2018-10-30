package com.yang.redis.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonConfig {
	
	private static final String ADDRESS = "redis://192.168.1.3:6379";
	
	public static RedissonClient getClient(){
		Config config = new Config();
		config.useSingleServer().setAddress(ADDRESS);
		return Redisson.create(config);
	}


}
