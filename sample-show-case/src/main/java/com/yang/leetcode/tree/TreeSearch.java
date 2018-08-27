package com.yang.leetcode.tree;

import com.yang.leetcode.TreeNode;

import java.util.Stack;
import java.util.ArrayList;

public class TreeSearch {

    /**
     * 二叉树-后续遍历-不使用递归实现
     *
     * 这个解法可能是最佳实践，思路清晰，易于理解,核心思想是用栈做辅助空间。
     *
     * 1.先从根往左一直入栈，直到为空。
     * 2.然后判断栈顶元素的右孩子，如果不为空且未被访问过，
     *
     * 3.则从它开始重复左孩子入栈的过程；否则说明此时栈顶为要访问的节点（因为左右孩子都是要么为空要么已访问过了），
     *
     * 出栈然后访问即可，接下来再判断栈顶元素的右孩子...直到栈空。
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        TreeNode currentNode = root;

        TreeNode pre = null;   //pre表示上次遍历的节点，用来判断之前是否被遍历过

        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();

        while(currentNode != null || !stack.isEmpty()) {

            //左孩子一直入栈，直到左孩子为空
            if(currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
                continue;
            }
            //如果是有孩子
            currentNode = stack.peek();
            currentNode = currentNode.right;
            if(currentNode != null && currentNode != pre) {   //如果栈顶元素的右孩子不为空，且未被访问过
                stack.push(currentNode);              //则右孩子进栈，然后重复左孩子一直进栈直到为空的过程
                currentNode = currentNode.left;
            } else {
                currentNode = stack.pop();            //否则出栈，访问，r记录刚刚访问的节点
                list.add(currentNode.val);
                pre = currentNode;
                currentNode = null;
            }

        }

        return list;
    }


    /**
     * 二叉树-前序遍历-不使用递归实现
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root == null)
            return list;

        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if(temp.right != null)
                stack.push(temp.right);
            if(temp.left != null)
                stack.push(temp.left);
        }

        return list;
    }


}
