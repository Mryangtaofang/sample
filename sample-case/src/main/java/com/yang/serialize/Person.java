package com.yang.serialize;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = -286971142225748665L;
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public Person(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}



	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
