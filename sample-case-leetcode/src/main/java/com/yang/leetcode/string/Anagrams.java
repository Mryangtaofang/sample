package com.yang.leetcode.string;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Anagrams {
	
	 //the fatest way is to use sliding window
	 public List<Integer> findAnagrams(String s, String p) {
	        int slen = s.length();
	        int plen = p.length();
	        
	        List<Integer> res = new ArrayList<>();
	        if (plen > slen) return res;
	        
	        int[] hash = new int[26];
	        for (char pChar : p.toCharArray()) 
	            hash[pChar - 'a']++;
	        
	        int count = plen;
	        
	        //now we have all p in the hash array, if there is data value > 0, else vale = 0
	        //use two pointers, one for begin, one for end
	        //each time end++, then check if hash has value > 0, then--, count--

	        int start = 0, end = 0;
	        
	        while (end < slen) {
	            //always --, but only count if there is one in the hash
	            if (hash[s.charAt(end) - 'a'] > 0) 
	                count--;
	            
	            hash[s.charAt(end) - 'a']--;
	            end++;
	            
	            if (count == 0) res.add(start);
	            
	            if (end - start == plen) {
	                //always ++, but only count if there is one in the hash count means need more to --
	                if (hash[s.charAt(start) - 'a'] >= 0) {
	                    count++;
	                }
	                hash[s.charAt(start) - 'a']++;//note, if it is not in the hash, it will be only add to zero
	                start++;

	            }
	        }
	        return res;
	    }
	 
	 
	   public int hammingWeight(int n) {
	        int sum = 0;
	        while(n>0){
	           if((n&1) == 1)
	               sum++;
	            
	            n = n>>>1;
	        }
	        
	        return sum;
	    }
    @Test
    public void testCase(){
    	System.out.print(new Anagrams().hammingWeight(1));
    }
    
}
