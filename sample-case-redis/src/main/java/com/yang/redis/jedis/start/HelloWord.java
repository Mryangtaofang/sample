package com.yang.redis.jedis.start;

import redis.clients.jedis.Jedis;

public class HelloWord {

	
	public static void main(String[] args){
		Jedis jedis = new Jedis("192.168.1.3", 6379);
		jedis.set("name", "bar");
		String name = jedis.get("name");
		System.out.println(name);
		jedis.close();
	}
}
