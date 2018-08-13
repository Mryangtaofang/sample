package com.yang.j2se;

import com.yang.serialize.Person2;


public class Person implements Cloneable{
	private String name;
	private Integer age;
	
	private Person2 person2;

	public String getName() {
		return name;
	}

	public Person() {
		System.out.println("调用了Person的构造方法");
	}


	public void setName(String name) {
		System.out.println("调用了Person的setName方法");
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		System.out.println("调用了Person的setAge方法");
		this.age = age;
	}
	
	public Person2 getPerson2() {
		return person2;
	}

	public void setPerson2(Person2 person2) {
		this.person2 = person2;
	}

	@Override
	public Person clone() throws CloneNotSupportedException {
		return (Person)super.clone();
	}

}
