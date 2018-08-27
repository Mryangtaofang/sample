package com.yang.leetcode.tree;

/**
 * 	给一个二叉树，求这个二叉树叶子节点，最浅的层数
 *	Given a binary tree, find its minimum depth.
 *	The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MiniDepthBinaryTree {

    public int run(TreeNode root) {
        return (root == null) ? 0 : getMinDepth(root);
    }
    
    public int getMinDepth(TreeNode root){
    	if(root.left == null && root.right == null){
    		return 1;
    	}
    	
    	if(root.left != null && root.right != null){
    		int leftDepth  =  getMinDepth(root.left);
        	int rightDepth = getMinDepth(root.right);
            return Math.min(leftDepth,rightDepth)+1;
    	}
    
    	return root.right==null ? getMinDepth(root.left)+1 : getMinDepth(root.right)+1;
    }

}
