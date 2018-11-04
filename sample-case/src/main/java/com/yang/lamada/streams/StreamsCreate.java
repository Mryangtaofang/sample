package com.yang.lamada.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsCreate {

	
	public static void main(String[] args){
		
		// Stream中封装了一些静态方法可以用来创建 Stream,of就是一个
		Stream<String> stream = Stream.of("a","b", "c");
		stream.forEach(e -> System.out.println(e));

		String [] strArray = new String[]{"a", "b", "c"};
		stream = Stream.of(strArray);
		stream.forEach(System.out::println);
		
		//Arrays和collection中也有stream方法
		stream = Arrays.stream(strArray);
		List<String> list =Arrays.asList(strArray);

		list.stream().forEach(System.out::println);
		
		intStream();
	}
	
	
	/**
	 * 需要注意的是，对于基本数值型，目前有三种对应的包装类型 Stream：IntStream、LongStream、DoubleStream。
	 * 当然我们也可以用 Stream<Integer>、Stream<Long> >、Stream<Double>，
	 * 但是 boxing 和 unboxing 会很耗时，所以特别为这三种基本数值型提供了对应的 Stream。
	 */
	public static void intStream(){
		// 数值流的构造
		IntStream.of(new int[]{1, 2,3}).forEach(System.out::println);
		IntStream.range(1,4).forEach(System.out::println);
		IntStream.rangeClosed(1,3).forEach(System.out::println);
	}
	
}
