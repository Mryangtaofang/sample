package com.yang.lamada.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsCase {

	private static List<Person> javaProgrammers = new ArrayList<Person>(){
		private static final long serialVersionUID = 1L;

	{    
	    add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));  
	    add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));  
	    add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));  
	    add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));  
	    add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));  
	    add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));  
	    add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));  
	    add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));  
	    add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));  
	    add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));  
	}};  
	
	private static List<Person> phpProgrammers = new ArrayList<Person>() {
		private static final long serialVersionUID = 1L;

	{  
	    add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));  
	    add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));  
	    add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));  
	    add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));  
	    add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));  
	    add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));  
	    add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));  
	    add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));  
	    add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));  
	    add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));  
	}};  
	
	public static void main(String[] args){
		
		System.out.println("所有程序员的姓名:");  
		javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));  
		phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));  
		
		
//		if(CollectionUtils.isEmpty(phpProgrammers));
		
		StringBuffer sb = new StringBuffer();
		phpProgrammers.forEach(p->{
			sb.append(p.getLastName());
		});
		System.out.println("给程序员加薪 5% :");  
		//查看源码就知道，javaProgrammers.forEach中，
		//forEach是jdk1.8在Iterable中新增的一个方法
		//forEach其实接收的是一个Consumer类型的参数
		Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());  
		  
		javaProgrammers.forEach(giveRaise);  
		phpProgrammers.forEach(giveRaise);  

		//filter
		testFilter();
		
		//limit
		testLimit();
	}
	
	/**
	 * Filter
	 */
	public static void testFilter(){
		System.out.println("下面是月薪超过 $1,400 的PHP程序员:"); 
		//stream属于collection中的方法,collection继承与Iterable
		//stream()返回的是一个Stream对象，Stream在1.8里新增的
		//filter(Predicate<? super T> predicate)接受的是一个Predicate类型的参数,返回的是Stream结果
		phpProgrammers.stream()  
		          .filter((p) -> (p.getSalary() > 1400))  
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
		
		
		// 定义 filters  
		Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);  
		Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);  
		Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));  
		
		System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");  
		phpProgrammers.stream()  
		          .filter(ageFilter)  
		          .filter(salaryFilter)  
		          .filter(genderFilter)  
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));  
	}
	
	/**
	 * Limit
	 */
	public static void testLimit(){
		System.out.println("最前面的3个 Java programmers:");  
		javaProgrammers.stream()  
		          .limit(3)  
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));  
		
		
		System.out.println("最前面的3个女性 Java programmers:");  
		javaProgrammers.stream()  
		          .filter((p) -> ("female".equals(p.getGender())))  
		          .limit(3)  
		          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName())); 
	}
	
	/**
	 * sort 
	 */
	public static void testSort(){
		System.out.println("根据 name排序,并显示前5个 Java programmers:");  
		List<Person> sortedJavaProgrammers = javaProgrammers  
		          .stream()  
		          .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))  
		          .limit(5)  
		          .collect(Collectors.toList());  
		  
		sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));  
		   
		System.out.println("根据 salary 排序 Java programmers:");  
		sortedJavaProgrammers = javaProgrammers  
		          .stream()  
		          .sorted( (p, p2) -> (p.getSalary() - p2.getSalary()) )  
		          .collect(Collectors.toList() );  
		  
		sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName())); 
	}
}
