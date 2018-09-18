package com.yang.jvm;

public class SubClassInitProcess extends ClassInitProcess{
	
	public static final String sub_final_static_string = "SubClassInitProcess.final_static_string";
	
	public static String sub_static_string = "SubClassInitProcess.hello world";
	
	static {
		System.out.println("SubClassInitProcess init!");
	}
	
}
