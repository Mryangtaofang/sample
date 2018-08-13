package com.yang.leetcode;

import java.util.Stack;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
 *
 */
public class Fibonacci {
	
    public int fibonacci(int n) {
    	if(n <= 0){
    		return 0;
    	}
    	Stack<Integer> newStack = new Stack<Integer>();
    	Stack<Integer> oldStack = new Stack<Integer>();
    	newStack.push(1);oldStack.push(1);
    	if(n <= 2){
    		return newStack.pop();
    	}
		while (n > 2) {
			int newNum = newStack.pop();
			int oldNum = oldStack.pop();
			newStack.push(newNum + oldNum);
			oldStack.push(newNum);
			n--;
		}
    	return newStack.pop();
    }
    
    public int fibonacci1(int n) {
    	if(n <= 0){
    		return 0;
    	}
    	Stack<Integer> newStack = new Stack<Integer>();
    	Stack<Integer> oldStack = new Stack<Integer>();
    	newStack.push(1);oldStack.push(1);
    	if(n <= 2){
    		return newStack.pop();
    	}
		while (n > 2) {
			int newNum = newStack.pop();
			int oldNum = oldStack.pop();
			newStack.push(newNum + oldNum);
			oldStack.push(newNum);
			n--;
		}
    	return newStack.pop();
    }
}
