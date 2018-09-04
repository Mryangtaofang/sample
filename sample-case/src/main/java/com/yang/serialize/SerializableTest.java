package com.yang.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.yang.clone.Person2;

public class SerializableTest {

	@Test
    public void testSerializable() throws Exception{
        File file = new File("D:\\person.txt");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("Taylor Swift", 28);
        out.writeObject(person);
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Person newPerson = (Person) in.readObject();
        System.out.println(newPerson.getName());
        in.close();
    }
    
	@Test
    public void testExternalizable() throws Exception{
    	File file = new File("D:\\person.txt");

        //调用无参构造方法
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        Person2 person = new Person2();
        out.writeObject(person);
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Person2 newPerson = (Person2) in.readObject(); 
        System.out.println(newPerson);
        in.close();
    }
}
