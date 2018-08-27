package com.yang.leetcode.linkedlist;


public class FindKthToTail {
	/**
	 * 输入一个链表，输出该链表中倒数第k个结点。
	 * 一次遍历即可
	 */
    public ListNode findKthToTail(ListNode head,int k) {
        if(head == null || k<=0) return null;
        ListNode kNode,currentNode;
        kNode = currentNode = head;
        
        do{
        	if(k>0)
        		k--;
        	else
        		kNode = kNode.next;
        	
            if(currentNode.next == null){
            	if(k>0) return null;
            	break;
            }
            currentNode = currentNode.next;
        }while(true);

        return kNode;
    }

    
    /**
     * 输入一个链表，反转链表后，输出链表的所有元素。
     */
    public ListNode ReverseList(ListNode head) {
    	//其实就是链表的头插法
    	 if(head == null) return null;
    	 ListNode newHead,newNode;
    	 newHead = newNode = null;
    	 do{
    		 newNode = head;
    		 head = head.next;
    		 newNode.next = newHead;
    		 newHead = newNode;
    	 }while(head != null);
    	 return newHead;
    }
    
    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     */
    public ListNode merge(ListNode list1,ListNode list2) {
    	if(list1 == null) return list2;
    	if(list2 == null) return list1;
    	
    	ListNode resultList,newNode,currentNode;
    	resultList = newNode = currentNode = null;  	
    	
    	while(list1 != null && list2 != null){
    		if(list1.val > list2.val){
    			newNode = list2;
    			list2 = list2.next;
    		}else{
    			newNode = list1;
    			list1 = list1.next;
    		}
    		if(resultList == null){
    			resultList = currentNode = newNode;
    		}else{
    			currentNode.next = newNode;
        		currentNode = newNode;
    		}
    	}
    	
    	if(list1 != null){
    		currentNode.next = list1;
    	}
    	
    	if(list2 != null){
    		currentNode.next = list2;
    	}
    	
        return resultList;
    }
}


