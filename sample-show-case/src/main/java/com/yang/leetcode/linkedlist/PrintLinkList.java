package com.yang.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

import com.yang.leetcode.ListNode;


/**
 * 反向打印单链表的值
 */
public class PrintLinkList {
	
	//头插法
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	ListNode start = listNode;
    	while(start != null ){
    		list.add(0, start.val);
    		start = start.next;
    	}
    	return list;
    }
    
    @Test
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	Stack<Integer> stack = new Stack<Integer>();
    	ListNode start = listNode;
    	while(start != null ){
    		stack.push(start.val);
    		start = start.next;
    	}
    	while(stack != null)
    		list.add(stack.pop());
    	
    	return list;
    }

}



