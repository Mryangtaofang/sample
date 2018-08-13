package com.yang.leetcode;

import java.util.Stack;

/**
 * 输入表达式，输出结果
 */
public class ReversePolishNotation {
	
    public int evalRPN(String[] tokens) {
    	if(tokens == null || tokens.length <= 0){
    		return 0;
    	}
        Stack<Integer> intStack = new Stack<Integer>();
        int firstNum,secondNum = 0;
        for(String token:tokens){
        	if("+".equals(token)){
        		firstNum = intStack.pop();
        		secondNum = intStack.pop();
        		intStack.push(firstNum+secondNum);
        	}else if("-".equals(token)){
        		firstNum = intStack.pop();
        		secondNum = intStack.pop();
        		intStack.push(secondNum - firstNum);
        	}else if("*".equals(token)){
        		firstNum = intStack.pop();
        		secondNum = intStack.pop();
        		intStack.push(firstNum*secondNum);
        	}else if("/".equals(token)){
        		firstNum = intStack.pop();
        		secondNum = intStack.pop();
        		if(firstNum == 0)
        			throw new RuntimeException("表达式中，被除数为0");
        		intStack.push(secondNum/firstNum);
        	}else{
        		try {
					intStack.push(Integer.valueOf(token));
				} catch (NumberFormatException e) {
					throw new RuntimeException("表达式异常!");
				}
        	}
        }
        return intStack.pop();
    }
    
    
}
