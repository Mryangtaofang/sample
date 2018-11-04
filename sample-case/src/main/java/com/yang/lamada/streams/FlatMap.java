package com.yang.lamada.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * https://blog.csdn.net/caihuangshi/article/details/51298782
 * flatMap把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
 * 最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。
 */
public class FlatMap {

	public static void main(String[] args) {
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2, 3), 
				Arrays.asList(4, 5, 6)
		);
		
		inputStream.flatMap((childList) -> childList.stream()).forEach(System.out::println);;
	}

}
