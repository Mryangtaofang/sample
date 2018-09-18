package com.yang.jvm;

public class ClassInitProcess {

	public static final String final_static_string = "ClassInitProcess.final_static_string";
	
	public static  String static_string = "ClassInitProcess.hello world";
	
	static {
		System.out.println("ClassInitProcess init!");
	}
	
}
