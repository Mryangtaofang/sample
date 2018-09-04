package com.yang.leetcode.tree;

import org.junit.Test;


/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 * 则重建二叉树并返回。
 */
public class RebuildTree {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    	if(pre == null || pre.length <= 0 || in == null || in.length <= 0){
    		return null;
    	}
    	
        return getTree(pre,in,0,0,pre.length);
    }
    
    public TreeNode getTree(int [] pre,int [] in,int preIndex,int inIndex,int len){
    	int start = pre[preIndex];
    	TreeNode head = new TreeNode(start);
    	if(len <= 0){
    		return head;
    	}
    	int index = 0;
    	while(index < len && in[inIndex+index] != start){
    		index++;
    	}
		preIndex++;
		if(index > 0){
			head.left = getTree(pre, in, preIndex, inIndex, index);
		}
		if(index+1 < len){
			head.right = getTree(pre, in, preIndex + index, inIndex + index + 1,len - index - 1);	
		}
		
    	return head;
    }
    
    
    
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	@Test
	public void testTree(){
		int [] pre = {1,2,4,7,3,5,6,8};
		int [] in = {4,7,2,1,5,3,8,6};
		new RebuildTree().reConstructBinaryTree(pre,in);
	}
}
