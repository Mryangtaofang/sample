package com.yang.tree;

public class AVLNode<E> {
	E element;
    AVLNode<E> left;
    AVLNode<E> right;
    int height;

    public AVLNode(E element) {
        this(element, null, null);
    }

    public AVLNode(E element, AVLNode<E> left, AVLNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
