package com.yang.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 *	
 *	Return all such possible sentences.
 *	
 *	For example, given
 *	s ="catsanddog",
 *	dict =["cat", "cats", "and", "sand", "dog"].
 *	
 *	A solution is["cats and dog", "cat sand dog"].
 *
 *  思路：
 *  将字符串切割为两部分左边s1和右边s2，如果s1包含在字典中，则递归计算s2切割生成的字符串集合，并将s1和s2返回的结果并和。
 *  s2可能无法切割，我们让其返回一个空的ArrayList。
 *  s为空串是我们规定返回包含一个""的ArrayList.
 *  仅仅递归会超时，我们用一个map记录字符串对应的结果。
 */
public class WordBreakII {


	public ArrayList<String> wordBreak(String word, Set<String> dict) {
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		//放置一个空的字符分词集合
		ArrayList<String> blankWord = new ArrayList<String>();
		blankWord.add("");
		map.put("", blankWord);
		
		return dfs(word, dict, map);
	}
	     
	public ArrayList<String> dfs(String word, Set<String> dict,HashMap<String, ArrayList<String>> map) {
		//如果map中已经包含了这个词，返回
		if (map.containsKey(word))
			return map.get(word);

		ArrayList<String> lists = new ArrayList<String>();
		
		int len = word.length();
		for (int i = 1; i <= len; i++) {
			String first = word.substring(0, i);
			//如果包含前半部分，则对后半部分进行递归
			if (dict.contains(first)){
				//递归，求解后一部分的分词结果
				ArrayList<String> last = dfs(word.substring(i, len), dict, map);
				if (last.size() == 0) 
					continue;
				
				//拼装拆分结果
				for (String wordPart : last) {
					StringBuilder sb = new StringBuilder(first)
										.append(" ")
										.append(wordPart);
					
					lists.add(sb.toString().trim());
				}
			}
		}
		
		map.put(word, lists);
		return lists;
	}
}
