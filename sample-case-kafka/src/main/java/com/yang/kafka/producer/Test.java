package com.yang.kafka.producer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.yang.kafka.User;

public class Test {

	public static void main(String[] args) {
        Type genType = Child.class.getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        System.out.println(params);
        System.out.println(params[0]);
    }


	class FatherClass<Protobufable> {
		
	}



	class Child extends FatherClass<User> {
		
	}
}
