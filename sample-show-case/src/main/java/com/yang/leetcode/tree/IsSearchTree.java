package com.yang.leetcode.tree;

import org.junit.Test;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class IsSearchTree {

	/**
	 * 解法：二叉搜索树左子树的所有节点都比根节点小，右子树都比根节点大，而最后的一个元素，是当前子树的根节点
	 */
    public boolean VerifySquenceOfBST(int [] sequence) {
    	if(sequence == null || sequence.length < 1)
    		 return false;
    	
    	if(sequence.length == 1)
    		return true;
    	
		return isBst(sequence, 0, sequence.length - 1);
    }
    
    public boolean isBst(int [] sequence,int start,int end){
    	if(start >= end)
    		return true;
    	
    	int root = sequence[end];
    	
    	int leftEnd = start ;
    	//如果比根节点大，说明他在右子树，否在在左子树
		for (;leftEnd < end; leftEnd++) {
			if(sequence[leftEnd] > root) break;
		}
		
		int rightStart = leftEnd;
		for (; rightStart < end; rightStart++) {
			//如果右子树中存在比根节点大的元素，那么他肯定不是二叉搜索树
			if (sequence[rightStart] < root)
				return false;
		}
		
    	return isBst(sequence,start,leftEnd-1) && isBst(sequence,rightStart,end-1);
    }
    
    @Test
    public void testBst(){
    	int [] arr = new int[]{1,2,3,4,5};
    	new IsSearchTree().VerifySquenceOfBST(arr);
    }
}
