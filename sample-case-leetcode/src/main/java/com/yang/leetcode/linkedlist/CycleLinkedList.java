package com.yang.leetcode.linkedlist;

import com.yang.leetcode.ListNode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 *
 * 解法：
 * 快慢指针
 * 如果快指针和慢指针相遇，则说明有环。
 */
public class CycleLinkedList {

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null)
            return false;
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow)
                return true;
        }
        
        return false;
    }
}
