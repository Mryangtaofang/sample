package com.yang.thread;

import java.util.ArrayList;
import java.util.List;



public class TestSulotion {

	public class Node {
		int id;
		int parentId;
		Node[] children;
	}
	
	public Node getTree(Node[] nodes) {
	if(nodes == null || nodes.length <=0){
		return null;
	}
	Node headNode = null;
	
	
	for(Node node : nodes){
		if(node.parentId == 0)
			headNode = node;
		int parentId = node.parentId;
		setChild(node,nodes,parentId);
	}
	
	return headNode;
	}
	
	private void setChild(Node parentNode,Node[] nodes,int parentId) {
	List<Node> childs = new ArrayList<TestSulotion.Node>();
	for(Node node : nodes){
		if(node.parentId == parentId){
			childs.add(node);
		}
	}
	parentNode.children = (Node[])childs.toArray();
	}

}



