package com.yang.leetcode.tree;


/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *	输入描述:
 *	二叉树的镜像定义：源二叉树 
 *	    	    8
 *	    	   /  \
 *	    	  6   10
 *	    	 / \  / \
 *	    	5  7 9 11
 *	    	镜像二叉树
 *	    	    8
 *	    	   /  \
 *	    	  10   6
 *	    	 / \  / \
 *	    	11 9 7  5
 * @author yangyaming
 */
public class MirrorTree {
	
    public void Mirror(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) return ;
        swap(root);
        Mirror(root.left);
        Mirror(root.right);
    }
    
    public void swap(TreeNode node){
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
    
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
}
