package com.yang.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;


/**
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 * 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * 
 * 	    _ _ _ _ _
 *	   |		 |
 * 例子：A -> B -> C -> D -> E
 * 			|_ _ _ _ _|
 * 		
 * 解法:	
 *       _ _ _ _ _ _ _ _ _ _ _ 
 *		|	   			      |
 * 第一步：A -> A' -> B -> B' -> C -> C' -> D -> D' -> E -> E'
 * 			       |_ _ _ _ _ _ _ _ _ _ _|
 * 
 * 			  _ _ _ _ _ _ _ _ _ _ _ 
 *       _ _ + _ _ _ _ _ _ _ _ 	   |	
 *		|	 | 			      |    |
 * 第二步：A -> A' -> B -> B' -> C -> C' -> D -> D' -> E -> E'
 * 			       |_ _ | _ _ _ _ _ _ _ _|	  |
 * 						+ _ _ _ _ _ _ _ _ _ _ |
 * 
 * 	     _ _ _ _ _ _
 *	    |    	    |
 * 第三步：A' -> B' -> C' -> D' -> E'
 * 			  |_ _ _ _ _ _|
 */
public class CopyComplexLinkedList {

	List<RandomListNode> list = new ArrayList<RandomListNode>();
	
    public RandomListNode Clone(RandomListNode pHead){
    	if(pHead == null)
    		return null;
    	
    	//第一步
    	RandomListNode currentNode = pHead;
    	while(currentNode != null){
    		RandomListNode node =  new RandomListNode(currentNode.label);
    		node.next = currentNode.next;
    		currentNode.next = node;
    		currentNode = node.next;
    	}
    	//第二步
    	currentNode = pHead;
    	while(currentNode != null){
    		if(currentNode.random != null)
    			currentNode.next.random = currentNode.random.next;
    		currentNode = currentNode.next.next;
    	}
    	//第三步
    	currentNode = pHead;
    	RandomListNode newHead = currentNode.next;
    	RandomListNode cloneCurrentNode = currentNode.next;
    	while(currentNode != null){
    		currentNode.next = currentNode.next.next;
    		if(cloneCurrentNode.next!=null)
    			cloneCurrentNode.next = cloneCurrentNode.next.next;
    		
    		cloneCurrentNode = cloneCurrentNode.next;
    		currentNode = currentNode.next;
    	}
    	return newHead;
    }
      

	/**
	 * linklist Node
	 */
	public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
