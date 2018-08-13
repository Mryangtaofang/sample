package com.yang.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.List;

public class CopyComplexLinkedList {

	List<RandomListNode> list = new ArrayList<RandomListNode>();
	
    public RandomListNode Clone(RandomListNode pHead){
    	RandomListNode head = cloneNode(pHead);
    	cloneRandomNode(pHead);
    	return head;
    }
    
    private void cloneRandomNode(RandomListNode node) {
    	if(node == null)
    		return ;
	}

	private RandomListNode cloneNode(RandomListNode node) {
    	if(node == null)
    		return null;
    	
    	RandomListNode currentNode = new RandomListNode(node.label);
    	if(currentNode.next != null)
    		currentNode.next = this.cloneNode(node.next);
    	currentNode.random = node.random;
    	list.add(node);
    	return currentNode;
	}


	public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
