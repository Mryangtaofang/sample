package com.yang.leetcode.stack;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型
 * @author yangyaming
 */
public class QueueByStack {

    Stack<Integer> first = new Stack<Integer>();
    Stack<Integer> second = new Stack<Integer>();
    
    public void push(int node) {
    	first.push(node);
    }
    
    public int pop() {
    	if(first.size() <= 0){
    		return -1;
    	}

    	while(first.size() > 0){
    		second.push(first.pop());
    	}
    	int result = second.pop();
    	
    	while(second.size() > 0){
    		first.push(second.pop());
    	}
    	
    	return result;
    }
}
