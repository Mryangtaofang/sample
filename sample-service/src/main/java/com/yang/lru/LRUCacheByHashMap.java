package com.yang.lru;

import java.util.HashMap;

public class LRUCacheByHashMap {

	int capacity;
	HashMap<Integer, Node> map = new HashMap<Integer, Node>();

	Node head = null; //头部
	Node end = null; 

	class Node {
		int key;
		int value;
		Node pre;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	public LRUCacheByHashMap(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {

		if (map.containsKey(key)) {
			Node current = map.get(key);
			remove(current);
			setHead(current);

			return current.value;
		}
		return -1;

	}
	
	public void put(int key, int value) {

		if (map.containsKey(key)) {
			Node current = map.get(key);
			current.value = value;
			remove(current);
			setHead(current);
		} else {
			Node current = new Node(key, value);

			if (map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
			}

			setHead(current);
			map.put(key, current);
		}

		return;
	}

	private void remove(Node current) {
		if (current.pre != null)
			current.pre.next = current.next;
		else
			head = head.next; 

		if (current.next != null)
			current.next.pre = current.pre;
		else
			end = current.pre;
		return;
	}

	private void setHead(Node n) {
		n.next = head;
		n.pre = null;
		
		if (head != null)
			head.pre = n;

		head = n;

		if (end == null)
			end = head;
	}
}
