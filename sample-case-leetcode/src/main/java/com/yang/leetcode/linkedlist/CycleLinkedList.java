package com.yang.leetcode.linkedlist;

import com.yang.leetcode.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * 题目描述: 链表的入环节点，如果无环，返回null
 * 思路：
 * 1）首先判断是否有环,有环时，返回相遇的节点，无环，返回null
 * 2）有环的情况下， 求链表的入环节点
 *   fast再次从头出发，每次走一步，
 *   slow从相遇点出发，每次走一步，
 *   再次相遇即为环入口点。
 *   
 *   
 *  1）同linked-list-cycle-i一题，使用快慢指针方法，判定是否存在环，并记录两指针相遇位置(Z)；
 *	2）将两指针分别放在链表头(X)和相遇位置(Z)，并改为相同速度推进，则两指针在环开始位置相遇(Y)。
 *	
 *	证明如下：
 *	如下图所示，X,Y,Z分别为链表起始位置，环开始位置和两指针相遇位置，则根据快指针速度为慢指针速度的两倍，可以得出：
 *	2*(a + b) = a + b + n * (b + c)；即
 *	a=(n - 1) * b + n * c = (n - 1)(b + c) +c;
 *	注意到b+c恰好为环的长度，故可以推出，如将此时两指针分别放在起始位置和相遇位置，并以相同速度前进，当一个指针走完距离a时，另一个指针恰好走出 绕环n-1圈加上c的距离。
 *	故两指针会在环开始位置相遇
 *
 *
 *				   7 <- 6    
 *				  \|/   +
 *				   +   /|\
 *	1 -> 2 -> 3 -> 4 -> 5 
 *  ^              ^    ^     ^
 *  |-------a------|- b-|--c--|
 *  X              Y    Z     Y'
 *  
 */
public class CycleLinkedList {
	     
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
         
        ListNode meetNode = meetingNode(head);
        //说明无环
        if (meetNode == null)
            return null;
         
        ListNode fast = head;
        ListNode slow = meetNode;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
         
        return slow;
    }
	     
    //寻找相遇节点，如果无环，返回null
    public ListNode meetingNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) 
                return slow;
        }
        return null;
    }
	
}
