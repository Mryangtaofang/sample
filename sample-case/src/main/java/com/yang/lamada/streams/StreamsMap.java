package com.yang.lamada.streams;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsMap {

	
	public static void main(String[] args){
			Stream<String> stringStream = Stream.of("yangyaming","zhangzhixiong","taofang");
			
			LinkedList<String> list = stringStream.map(String::toUpperCase).collect(Collectors.toCollection(LinkedList::new));
			
			Stream.of(list.stream().collect(Collectors.joining(","))).forEach(System.out::println);
	}
}
