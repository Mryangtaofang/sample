package com.yang.leetcode.linkedlist;


import com.yang.leetcode.ListNode;

/**
 * Given a singly linked list L: L0 → L1 → …→ Ln-1 → Ln,
 * reorder it to: L0 → Ln → L1 → Ln-1 → L2→ Ln-2 →…
 * You must do this in-place without altering the nodes' values.
 * For example,Given{1,2,3,4}, reorder it to{1,4,2,3}.
 *
 * 解法：
 * 1.快慢指针，将链表拆成两半，
 * 2.后一半链表，反转
 * 3.归并到第一段中
 */
public class ReorderLinkedList {

    public void reorderList(ListNode head) {
        if(head == null)
            return;

        /**1.快慢指针，将链表拆成两半，slow所指的就是后半段*/
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //截取后半段链表
        ListNode after = slow.next;
        slow.next = null;

        /**2.反转链表，newAfter即为反转后的head*/
        ListNode newAfter = null;
        while(after != null){
            ListNode node = after.next;
            after.next = newAfter;
            newAfter = after;
            after = node;
        }

        /**3.归并到第一段中*/
        ListNode current = head;
        while(newAfter != null){
            //取下带归并的一个节点
            ListNode node = newAfter;
            newAfter = newAfter.next;
            //插入到之前的链表中
            node.next = current.next;
            current.next = node;

            current = node.next;
        }
    }
}
