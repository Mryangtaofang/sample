package com.yang.leetcode.tree;

import com.yang.leetcode.TreeNode;

import java.util.ArrayList;

/**
 * 输入一颗二叉树的跟节点和一个整数，
 * 打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class SumEqualPath {
	
    private ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    
    private ArrayList<Integer> currentPath = new ArrayList<Integer>();
    
    
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
    	
        if(root == null)
        	return result;
        //将当前节点加入的遍历路径中
        currentPath.add(root.val);
        target -= root.val;
        
        if(target == 0 && isLeafNode(root))
            result.add(new ArrayList<Integer>(currentPath));
        
        if(target > 0){
            FindPath(root.left, target);
            FindPath(root.right, target);
        }
        
        //将当前节点从路径中移除，因需要遍历其他的路径，不包含此节点，其实是重用currentPath
        currentPath.remove(currentPath.size()-1);
        return result;
    }
	
	
	private boolean isLeafNode(TreeNode node){
		return (node.left == null && node.right == null);
	}
	
}

