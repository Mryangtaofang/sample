package com.yang.kafka.bulider;

import java.util.Properties;

public class PropertiesBuilder extends Properties{
	
	private static final long serialVersionUID = 1994071103897280550L;

	public static Properties me(){
		return new PropertiesBuilder();
	}
	
	public Properties servers(String servers){
		this.put("bootstrap.servers", servers);
		return this;
	}
	
	public Properties acks(String acks){
		this.put("acks", acks);
		return this;
	}
	
}
