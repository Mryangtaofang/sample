package com.yang.jvm.runtime;

import java.io.IOException;

public class RuntimeJVM {

	public static void main(String[] args){
		Runtime run = Runtime.getRuntime();	//通过Runtime类的静态方法获取Runtime类的实例
		
		System.out.println("JVM最大内存量：" + run.maxMemory());
		System.out.println("JVM空闲内存量：" + run.freeMemory());
		String str = "Hello"+"World";
		System.out.println(str);
		for(int i=0;i<2000;i++){
			str = str + i;
		}
		System.out.println("JVM空闲内存量：" + run.freeMemory());
		
		System.out.println("当前机器CPU的核数：" + Runtime.getRuntime().availableProcessors());
		
		try {
			run.exec("cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
