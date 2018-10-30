package com.yang.redis.map;


import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import com.yang.redis.redisson.config.RedissonConfig;

public class HelloWord {

	public static void main(String[] args){
		RedissonClient client = RedissonConfig.getClient();
		RMap<String,String> rmap  = client.getMap("yang_map");
		rmap.put("user_pin_1", "张三");
		rmap.put("user_pin_2", "李四");
		client.shutdown();
	}
}
