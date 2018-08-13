package com.yang.tree;

public class AVLTree<E extends Comparable<E>> {

	private AVLNode<E> root;

	public void insert(E element) {
		if(element == null) return; 
		root = insert(element, root);
	}

	public void remove(E element) {
		if(element == null) return; 
		remove(element, root);
	}

	public int height() {
		return height(root);
	}

	/** 插入新数据 */
	public AVLNode<E> insert(E element, AVLNode<E> parentNode) {
		if (parentNode == null) {
			return new AVLNode<E>(element);
		}
		// 先比较 是插左边还是插右边
		int compareResult = element.compareTo(parentNode.element);
		if(compareResult == 0){ //相等，代表树中已经存在了
			setNewHeight(parentNode);
			return parentNode;
		}
		
		if (compareResult < 0) {// 插到左子树上
			parentNode.left = insert(element, parentNode.left);
		} else {// 插到右子树上
			parentNode.right = insert(element, parentNode.right);
		}
		
		//左子树和右子树的差值
		int heightDifference = height(parentNode.left) - height(parentNode.right);
	
		if (heightDifference == 2) {
			// 如果等于2，说明平衡被打破了，需要进行调整。
			if (element.compareTo(parentNode.left.element) < 0) { 
				//小于表示element会被插到parentNode的左子树的左子树上，符合LL 用右旋转调整。
				parentNode = rightRotate(parentNode);
			} else {
				//大于会被插到parentNode的左子树的右子树上，符合LR，用先左旋转后右旋转来矫正。
				parentNode = leftAndRightRotate(parentNode);
			}
		}
		
		if(heightDifference == -2){
			// 如果等于-2，说明平衡被打破了，需要进行调整。
			if (element.compareTo(parentNode.right.element) > 0) {
				//小于表示element会被插到parentNode的左子树的左子树上，符合RR 用左旋转调整。
				parentNode = leftRotate(parentNode);
			} else {
				//大于会被插到parentNode的左子树的右子树上，符合RL，用先右旋转后左旋转来矫正。
				parentNode = rightAndLeftRotate(parentNode);
			}
		}
		
		setNewHeight(parentNode);
		return parentNode;
	}
	
	
	private void setNewHeight(AVLNode<E> node){
		node.height = Math.max(height(node.left), height(node.right)) + 1;
	}

	/**
	 * 删除数据
	 */
	private AVLNode<E> remove(E element, AVLNode<E> node) {
		if (node == null) return null;
		int compareResult = element.compareTo(node.element);
		if (compareResult < 0) {
			node.left = remove(element, node.left);
			// 完了之后验证该子树是否平衡
			if (node.right != null) { // 若右子树为空，则一定是平衡的，此时左子树相当对父节点深度最多为1,所以只考虑右子树非空情况
				if (node.left == null) { // 若左子树删除后为空，则需要判断右子树
					if (height(node.right) - node.height == 2) {
						AVLNode<E> k = node.right;
						if (k.right != null) { // 右子树存在，按正常情况单旋转
							node = leftRotate(node);
						} else { // 否则是右左情况，双旋转
							node = rightAndLeftRotate(node);
						}
					}
				}
				if (node.left != null) { // 否则判断左右子树的高度差
					// 左子树自身也可能不平衡，故先平衡左子树，再考虑整体
					AVLNode<E> k = node.left;
					// 删除操作默认用右子树上最小节点补删除的节点
					// k的左子树高度不低于k的右子树
					if (k.right != null) {
						if (height(k.left) - height(k.right) == 2) {
							AVLNode<E> m = k.left;
							if (m.left != null) { // 左子树存在，按正常情况单旋转
								k = rightRotate(k);
							} else { // 否则是左右情况，双旋转
								k = leftAndRightRotate(k);
							}
						}
					} else {
						if (height(k.left) - k.height == 2) {
							AVLNode<E> m = k.left;
							if (m.left != null) { // 左子树存在，按正常情况单旋转
								k = rightRotate(k);
							} else { // 否则是左右情况，双旋转
								k = leftAndRightRotate(k);
							}
						}
					}
					if (height(node.right) - height(node.left) == 2) {
						// 右子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
						node = leftRotate(node);
					}
				}
			}
			// 完了之后更新height值
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		} else if (compareResult > 0) {
			node.right = remove(element, node.right);
			// 下面验证子树是否平衡
			if (node.left != null) { // 若左子树为空，则一定是平衡的，此时右子树相当对父节点深度最多为1
				node = balanceChild(node);
			}
			// 完了之后更新height值
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		} else if (node.left != null && node.right != null) {
			// 默认用其右子树的最小数据代替该节点的数据并递归的删除那个节点
			AVLNode<E> min = node.right;
			while (min.left != null) {
				min = min.left;
			}
			// t.element = findMin(t.right).element;
			node.element = min.element;
			node.right = remove(node.element, node.right);
			node = balanceChild(node);
			// 完了之后更新height值
			node.height = Math.max(height(node.left), height(node.right)) + 1;
		} else {
			node = (node.left != null) ? node.left : node.right;
		}
		return node;
	}

	private AVLNode<E> balanceChild(AVLNode<E> t) {
		if (t.right == null) { // 若右子树删除后为空，则只需判断左子树与根的高度差
			if (height(t.left) - t.height == 2) {
				AVLNode<E> k = t.left;
				if (k.left != null) {
					t = rightRotate(t);
				} else {
					t = leftAndRightRotate(t);
				}
			}
		} else { // 若右子树删除后非空，则判断左右子树的高度差
			// 右子树自身也可能不平衡，故先平衡右子树，再考虑整体
			AVLNode<E> k = t.right;
			// 删除操作默认用右子树上最小节点（靠左）补删除的节点

			if (k.left != null) {
				if (height(k.right) - height(k.left) == 2) {
					AVLNode<E> m = k.right;
					if (m.right != null) { // 右子树存在，按正常情况单旋转
						k = leftRotate(k);
					} else { // 否则是右左情况，双旋转
						k = rightAndLeftRotate(k);
					}
				}
			} else {
				if (height(k.right) - k.height == 2) {
					AVLNode<E> m = k.right;
					if (m.right != null) { // 右子树存在，按正常情况单旋转
						k = leftRotate(k);
					} else { // 否则是右左情况，双旋转
						k = rightAndLeftRotate(k);
					}
				}
			}
			// 左子树自身一定是平衡的，左右失衡的话单旋转可以解决问题
			if (height(t.left) - height(t.right) == 2) {
				t = rightRotate(t);
			}
		}
		return t;
	}


	/**
	 * 右旋转
	 * @param 需要调整的树
	 * @return 调整后的树
	 */
	private AVLNode<E> rightRotate(AVLNode<E> node) {
		AVLNode<E> newTree = node.left;
		node.left = newTree.right;
		newTree.right = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newTree.height = Math.max(height(newTree.left), height(newTree.right)) + 1;
		return newTree;
	}

	/**
	 * 左旋转
	 * @param 需要调整的树
	 * @return 调整后的树
	 */
	private AVLNode<E> leftRotate(AVLNode<E> node) {
		AVLNode<E> newTree = node.right;
		node.right = newTree.left;
		newTree.left = node;
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		newTree.height = Math.max(height(newTree.left), height(newTree.right)) + 1;
		return newTree;
	}

	/**
	 * 先左旋后右旋
	 * @param 需要调整的树
	 * @return 调整后的树
	 */
	private AVLNode<E> leftAndRightRotate(AVLNode<E> t) {
		t.left = leftRotate(t.left);
		return rightRotate(t);
	}

	/**
	 * 先右旋后左旋
	 * @param 需要调整的树
	 * @return 调整后的树
	 */
	private AVLNode<E> rightAndLeftRotate(AVLNode<E> t) {
		t.right = rightRotate(t.right);
		return leftRotate(t);
	}

	/**
	 * 获取指定树的高度
	 */
	private int height(AVLNode<E> node) {
		return node == null ? -1 : node.height;
	}

	public void printTree() {
		printTree(root);
	}

	private void printTree(AVLNode<E> tree) {
		if (tree == null) {
			return;
		}
		System.out.print(tree.element + " ");
		printTree(tree.left);
		printTree(tree.right);

	}

}
