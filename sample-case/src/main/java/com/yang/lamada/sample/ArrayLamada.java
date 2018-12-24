package com.yang.lamada.sample;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class ArrayLamada {

	public void arrayLamada(int[] array){
		//将array生成stream,遍历
		IntStream intStream = Arrays.stream(array);
		//也可以IntStream intStream = IntStream.of(array);
		intStream.forEach(System.out::println);
		
		//将IntStream转化为Stream<Integer>
		Stream<Integer> streamInt = Arrays.stream(array).boxed();
		//Stream<Integer>得到List<Integer>
		List<Integer> intlist = streamInt.collect(Collectors.toList());
		
		intArrayToList(array).stream().forEach(System.out::println);
		
		IntStream.of(listToArray(intlist)).forEach(System.out::println);
	}
	
	/**
	 * 将Array转化为List
	 */
	public List<Integer> intArrayToList(int[] array){
		return Arrays.stream(array).boxed().collect(Collectors.toList());
	}
	
	/**
	 * 将List转化为Array
	 */
	public  int[] listToArray(List<Integer> list){
		return list.stream().mapToInt(Integer::intValue).toArray();
	}
	
	@Test
	public void testCase(){
		new ArrayLamada().arrayLamada(new int[] { 1, 2, 3 });
	}
}
