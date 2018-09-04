package com.yang.clone;

import org.junit.Test;

public class TestClone {

	@Test
	public void cloneTest() {
		Person person = new Person();
		person.setName("yang yang");
		person.setAge(25);
		Person2 person2 = new Person2();
		person2.setName("ty");
		person2.setAge(28);
		person.setPerson2(person2);
		try {
			Person person3 = person.clone();
			System.out.println("是否是同一个对象"+(person3.getName() == person.getName()));
			System.out.println("person2是否是同一个对象"+(person3.getPerson2() == person.getPerson2()));
			System.out.println(person3.getName());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
