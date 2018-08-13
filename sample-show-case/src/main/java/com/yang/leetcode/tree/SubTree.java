package com.yang.leetcode.tree;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class SubTree {
    public boolean HasSubtree(TreeNode aTree,TreeNode bTree) {
    	if(aTree == null || bTree == null) return false;
    	
    	if(!isSubtree(aTree,bTree))
    		return HasSubtree(aTree.left,bTree) || HasSubtree(aTree.right,bTree);
    	
    	return true;
    }
    
    public boolean isSubtree(TreeNode aTree,TreeNode bTree){
    	if(bTree == null ) return true;
    	
    	if(aTree == null) return false;
    	
    	if(aTree.val == bTree.val){
    		return isSubtree(aTree.left,bTree.left) && isSubtree(aTree.right,bTree.right);
    	}
    	return false;
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
