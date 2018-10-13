package com.yang.leetcode.tree;

import com.yang.leetcode.TreeNode;

/**
 *	Given a binary tree, find its minimum depth.
 *	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * 	给一个二叉树，求这个二叉树叶子节点，最浅的层数
 * 
 * 解法：
 * 递归求解
 * 1) 如果是叶子节点，返回1
 * 2) 如果左右子树都不为空，返回层数小的那个子树的层树+1，
 * 3) 如果右子树为空，返回左子树的层数。
 * 	      如果左子树为空，返回右子树。
 */
public class MiniDepthBinaryTree {

    public int run(TreeNode root) {
        return (root == null) ? 0 : getMinDepth(root);
    }
    
    public int getMinDepth(TreeNode root){
    	//1) 如果是叶子节点
    	if(root.left == null && root.right == null)
    		return 1;
    	//2) 如果左右子树都不为空
    	if(root.left != null && root.right != null)
            return Math.min(getMinDepth(root.left),getMinDepth(root.right))+1;
    	//3) 如果右子树为空 or 左子树为空
    	return root.right==null ? getMinDepth(root.left)+1 : getMinDepth(root.right)+1;
    }

}

/**
 * 举一反三:如果来求树的最大深度，那么只需要换一下比较方法就行了
 */

