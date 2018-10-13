package com.yang.leetcode.string;

import java.util.Stack;

import org.junit.Test;

public class ValidParentheses {

	public boolean isValid(String s) {
		if (s == null || s.equals(""))
			return false;

		Stack<Character> stack = new Stack<Character>();

		char[] sArr = s.toCharArray();

		for (char aChar : sArr) {
			switch (aChar) {
			case '(':
				stack.push(new Character(aChar));
				break;
			case '[':
				stack.push(new Character(aChar));
				break;
			case '{':
				stack.push(new Character(aChar));
				break;
			case ')':
				if (stack.isEmpty()){
					System.out.println("null");
				}
				Character c = stack.pop();
				if(c.charValue() != '(')
					System.out.println(c.charValue());

			case ']':
				if (stack.isEmpty() || stack.pop().charValue() != '[')
					return false;

			case '}':
				if (stack.isEmpty() || stack.pop().charValue() != '{')
					return false;
			}
		}

		return stack.isEmpty();
	}
	
	@Test
	public void testCase(){
		
		new ValidParentheses().isValid("()");
	}
}
