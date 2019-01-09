package com.yang.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 * @author yangyaming
 */
public class GroupAnagrams {
	
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0)
            return new ArrayList<>();
        
        HashMap<String,List<String>> map = new HashMap<>();
        
        for(String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            
            if(map.containsKey(key)){
            	map.get(key).add(str);
            }else{
            	List<String> newArray = new ArrayList<>();
            	newArray.add(str);
            	map.put(key, newArray);
            }
        }
        List<List<String>> reult = new ArrayList<>();
        for(java.util.Map.Entry<String,List<String>> item : map.entrySet()){
        	reult.add(item.getValue());
        }
        
        return reult;
    }
}
