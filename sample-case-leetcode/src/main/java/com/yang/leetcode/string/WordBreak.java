package com.yang.leetcode.string;

import java.util.Set;

public class WordBreak {

	/**
	 * 状态转移方程：
	 * f(i) 表示s[0,i]是否可以分词 
	 * f(i) = f(j) && f(j+1,i); 0 <= j < i;
	 */
	public boolean wordBreak(String inStr, Set<String> dict) {
		if (dict == null || dict.size() == 0)
			return false;

		int len = inStr.length();
		//表示arrays[i]，表示inStr,前i个字符，是否可以进行分词
		boolean[] canBreak = new boolean[len + 1];
		//默认，前0的字符，可以进行分词
		canBreak[0] = true;
		
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j < i; j++) {
				//如果前j个字符可以分词，并且从j到i的字符串也可以分词，那么，前i的字符就可以分词
				if (canBreak[j] && dict.contains(inStr.substring(j, i))) {
					canBreak[i] = true;
					break;
				}
			}
		}

		return canBreak[len];
	}
}
