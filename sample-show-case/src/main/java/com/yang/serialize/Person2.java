package com.yang.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person2 implements Externalizable{

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public Person2() {
//		System.out.println("调用了Person的构造方法");
	}
	
	public Person2(String name, Integer age) {
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
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		getName();
		getAge();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		setName("Taylor");
        setAge(28);
	}
	
    @Override
    public String toString() {
        return "[" + name + ", " + age +  "]";
    }

}
