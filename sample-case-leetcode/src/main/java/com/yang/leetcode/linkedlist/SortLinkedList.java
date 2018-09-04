package com.yang.leetcode.linkedlist;

import com.yang.leetcode.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * 思路：
 * 因为题目要求复杂度为O(nlogn),故可以考虑归并排序的思想。
 * 归并排序的一般步骤为：
 * 1）将待排序数组（链表）取中点并一分为二；
 * 2）递归地对左半部分进行归并排序；
 * 3）递归地对右半部分进行归并排序；
 * 4）将两个半部分进行合并（merge）,得到结果。
 *
 * 所以对应此题目，可以划分为三个小问题：
 * 1）找到链表中点 （快慢指针思路，快指针一次走两步，慢指针一次走一步，快指针在链表末尾时，慢指针恰好在链表中点）；
 * 2）写出merge函数，即如何合并链表。 （见merge-two-sorted-lists 一题解析）
 * 3）写出mergesort函数，实现上述步骤。
 */
public class SortLinkedList {

    /**
     * 链表的二路归并排序
     */
    public static ListNode sortList(ListNode head) {
        //空链表或则只有一个结点,直接返回head
        if(head == null || head.next==null)
            return head;

        //1. 将list 切分为两个部分
        ListNode prev=null, slow=head, fast=head;

        while (fast !=null && fast.next !=null){
            prev = slow;
            slow = slow.next;//slow指针后移一个数据
            fast = fast.next.next;//fast指针后移两个数据
        }

        prev.next = null;//将链表切断

        //分别排序前后两个部分
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        return merge(l1, l2);
    }

    /**
     * 合并两个有序链表
     */
    private static ListNode merge(ListNode fisrtHead, ListNode secondHead) {
        ListNode resultHead = new ListNode();
        ListNode current = resultHead;

        while (fisrtHead != null && secondHead != null) {
            if (fisrtHead.val < secondHead.val) {
                current.next = fisrtHead;
                fisrtHead = fisrtHead.next;
            } else {
                current.next = secondHead;
                secondHead = secondHead.next;
            }
            current = current.next;
        }

        current.next = (fisrtHead != null) ? fisrtHead : secondHead;

        return resultHead.next;
    }

}
