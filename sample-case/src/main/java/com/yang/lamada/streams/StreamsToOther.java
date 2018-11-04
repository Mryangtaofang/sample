package com.yang.lamada.streams;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * 流转换为其它数据结构
 * 
 * 
 * 
 * @author yangyaming
 *
 */
public class StreamsToOther {
	
	public static void main(String[] args){
		// 1. Array
		Stream<String> stream = Stream.of("a","b", "c");
		String[] strArray1 = stream.toArray(String[]::new);
		
		// 2. Collection
		//注意一个 Stream 只可以使用一次，如果没有下面这行stream = Stream.of("a","b", "c");，会报错
		stream = Stream.of("a","b", "c");
		List<String> list1 =stream.collect(Collectors.toList());
		
		stream = Stream.of("a","b", "c");
		List<String> list2 =stream.collect(Collectors.toCollection(LinkedList::new));
		
		stream = Stream.of("a","b", "c");
		Set<String> set1 =stream.collect(Collectors.toSet());
		
		stream = Stream.of("a","b", "c");
		Stack<String> stack1 = stream.collect(Collectors.toCollection(Stack::new));
		// 3. String
		stream = Stream.of("a","b", "c");
		String str =stream.collect(Collectors.joining(",")).toString();
		

		Stream.of(strArray1).forEach(System.out::println);
		
		list1.stream().forEach(System.out::println);
		list2.stream().forEach(System.out::println);
		
		set1.stream().forEach(System.out::println);
		
		stack1.stream().forEach(System.out::println);
		
		Stream.of(str).forEach(System.out::println);
	}
	
}
