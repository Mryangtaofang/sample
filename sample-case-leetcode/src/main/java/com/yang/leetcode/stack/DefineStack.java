package com.yang.leetcode.stack;

import java.util.Stack;

import org.junit.Test;

/**
 * 实现一个栈
 */
public class DefineStack {
	   static class Node{
	        int value;
	        Node next;
	        Node prev;
	        
	        public Node(int valueIn){
	            value = valueIn;
	        }
	    }
	    
	    volatile int cap = 1<<4;
		Node arr[] = new Node[cap];
		int topIndex = -1;
	    
	    Node head = null;
	    
	    /** initialize your data structure here. */
	    public DefineStack() {
	        
	    }
	    
	    public void push(int x) {
	        topIndex++;
	        
	        if(topIndex == cap) 
	            resize();
	        
	        arr[topIndex] = new Node(x);

	        if(head == null || head.value >= arr[topIndex].value){
	        	arr[topIndex].next = head;
	        	head = arr[topIndex];
	        } else {
	        	Node prev,cur;
	        	prev = cur = head;
	        	while(cur != null && cur.value < arr[topIndex].value){
	        		if(cur != prev)
	        			prev = prev.next;
	        		
	        		cur = cur.next;
	        	}
	        	
	        	arr[topIndex].next = cur;
	        	prev.next = arr[topIndex];
	        	arr[topIndex].prev = prev;
	        	
	        	if(cur != null)
	        		cur.prev = arr[topIndex];
	        }
	  
	    }
	    
	    public void pop() {
	        if(topIndex == -1)
	            return;
	        
	        Node removeNode = arr[topIndex];
	        
        	if(removeNode == head){
        		head = head.next;
        		head.prev = null;
        	}else{
        		removeNode.prev.next = removeNode.next;
        		
        		if(removeNode.next != null)
        		removeNode.next.prev = removeNode.prev;
        	}
	        
	        arr[topIndex] = null;
	        topIndex--;
	    }
	    
	    public int top() {
	        if(topIndex == -1)
	            return 0;
	        
	        return arr[topIndex].value;
	    }
	    
	    public int getMin() {
			return (head == null) ? 0 : head.value;
	    }
	    
	    private void resize(){
			cap = cap<<1;
			Node[] newArr = new Node[cap];
			System.arraycopy(arr, 0, newArr, 0, topIndex);
			arr = newArr;
	    }
    
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，
     * 请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如序列1,2,3,4,5是某栈的压入顺序，
     * 序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
     * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || popA == null ||
        		pushA.length == 0 || popA.length == 0 ||
        		pushA.length != popA.length) return false;
        Stack<Integer> st = new Stack<Integer>();
        int pushIndex = 0;
        int popIndex = 0;
        st.push(pushA[pushIndex++]);
        while(popIndex <= popA.length-1){
            while(popA[popIndex] != st.peek()){
                if(pushIndex == pushA.length) return false;
                st.push(pushA[pushIndex++]);
            }
            popIndex++;
            st.pop();
        }
        return true;
    }
	
    @Test
    public void testStack(){
    	DefineStack stack = new DefineStack();
    	stack.push(-2);
    	stack.push(0);
    	stack.push(-3);
    	stack.getMin();
    	stack.pop();
    	stack.top();
    }
}
