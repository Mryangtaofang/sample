package com.yang.lamada.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.CollectionUtils;

public class StreamsMap {

	
	public static void main(String[] args){
		System.out.println(stringArrayToString(Arrays.asList(1,2,3)));
	}
	
	public static String stringArrayToString(List<Integer> list){
		if(CollectionUtils.isEmpty(list))
			return null;
		
		return list.stream().map((s) -> ("'" + s + "'")).collect(Collectors.joining(",", "[", "]"));
	}
}
