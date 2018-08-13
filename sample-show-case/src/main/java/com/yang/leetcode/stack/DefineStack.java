package com.yang.leetcode.stack;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 实现一个栈
 */
public class DefineStack {
	volatile int cap = 1<<4;
	volatile int arr[] = new int[cap];
	volatile int topIndex = -1;
	ReentrantLock lock = new ReentrantLock();
	
    public void push(int node) {
    	lock.lock();
    	try {
			topIndex++;
			if(topIndex == cap) resize();
			arr[topIndex] = node;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
    }
    
    public void pop() {
    	try {
    		lock.lock();
            arr[topIndex] = 0;
            topIndex--;
    	} catch (Exception e) {
    		e.printStackTrace();
		}finally{
			lock.unlock();
		}

    }
    
    public int top() {
        return arr[topIndex];
    }
    
    public int min() {
    	try {
    		lock.lock();
	    	if(topIndex < 0 )
	    		return 0;
	    	
	    	int min = arr[0];
			for (int i = 1; i <= topIndex; i++) {
				if(arr[i] < min)
					min = arr[i];
			}
			return min;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return 0;
		}finally{
			lock.unlock();
		}
    }
    
    private void resize(){
		cap = cap<<1;
		int[] newArr = new int[cap];
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
    	stack.push(12);
    	stack.push(3);
    	stack.push(1);
    	stack.push(8);
    	stack.push(10);
    	stack.pop();
    }
}
