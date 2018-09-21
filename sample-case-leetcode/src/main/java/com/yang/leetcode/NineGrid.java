package com.yang.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 题目：我有1到8八个数字，放在一个3x3的九宫格里面，那么会留下一个空格。
 *
 *	 3  6  1
 *   2  4
 *   5  7  8
 *   
 *   空格可以和上下左右的数字进行交换，你可以认为空格在移动。如果移动成
 *   
 *   1  2  3
 *   4  5  6
 *   7  8
 *
 *	则游戏胜利。
 *
 *	你需要完成以下2件事情：
 *	1、给出数据结构来描述这个过程。
 *	2、给你一个初始状态，告诉我能不能胜利，并给出如何移动才能胜利。
 *	
 *	这有点像咱们中国的华容道游戏。
 *
 *	https://blog.csdn.net/csdnsevenn/article/details/82782947
 */
public class NineGrid {
	// 定义方向
	public static final int left = 1;
	public static final int right = 2;
	public static final int up = 3;
	public static final int down = 4;
	
	
	// 定义终点状态
	private static final Integer WIN_STATE = 123456780;
	
	// 保存已经搜索过的状态
	private Set<Integer>statusSet=new HashSet<Integer>();

	// 3x3的九宫格
	private int[][] arr;
	
	// 记录空格的位置
	private int x;
	private int y;
	
	// 定义移动的数组
	private List<Integer> moveArr = new LinkedList<Integer>();

	// 初始化，数字0代表空格，先遍历，找出空格的位置
    public NineGrid(int[][] arr) {
		this.arr = arr;
		for(int i=0; i<arr.length; i++){
			 for(int j=0; j<arr.length; j++) {
				 if(arr[i][j] == 0){
					 x = i;
					 y = j;
				}
			}
		}
	}
    
    // 判断是否可以朝某个方向进行移动
	public boolean canMove(int direction) {
		switch (direction) {
		case left://  y > 0才能左移
			return y > 0;
		case right://  y < 2才能右移
			return y < 2;
		case up://  x > 0才能上移
			return x > 0;
		case down://  x < 2才能下移
			return x < 2;
		}
		return false;
	}
	
	// 朝某个方向进行移动，该函数不作判断，直接移动
	// 调用前请自行用canMove先行判断
	public void move(int direction) {
		int temp;
		switch (direction) {

		case left:// 空格和左侧数字交换
			temp = arr[x][y - 1];
			arr[x][y - 1] = 0;
			arr[x][y] = temp;
			y = y - 1;
			break;

		case right: // 空格和右侧数字交换
			temp = arr[x][y + 1];
			arr[x][y + 1] = 0;
			arr[x][y] = temp;
			y = y + 1;
			break;

		case up:// 空格和上方数字交换
			temp = arr[x - 1][y];
			arr[x - 1][y] = 0;
			arr[x][y] = temp;
			x = x - 1;
			break;

		case down: //  空格和下方数字交换
			temp = arr[x + 1][y];
			arr[x + 1][y] = 0;
			arr[x][y] = temp;
			x = x + 1;
			break;
		}
	}

	//  某个方向的回退，该函数不作判断，直接移动
	//  其操作和move方法正好相反
	private void moveBack(int direction) {
		int temp;
		switch (direction) {
		//  空格和左侧数字交换
		case left:
			temp = arr[x][y + 1];
			arr[x][y + 1] = 0;
			arr[x][y] = temp;
			y = y + 1;
			break;
		//  空格和右侧数字交换
		case right:
			temp = arr[x][y - 1];
			arr[x][y - 1] = 0;
			arr[x][y] = temp;
			y = y - 1;
			break;
		//  空格和上方数字交换
		case up:
			temp = arr[x + 1][y];
			arr[x + 1][y] = 0;
			arr[x][y] = temp;
			x = x + 1;
			break;
		//  空格和下方数字交换
		case down:
			temp = arr[x - 1][y];
			arr[x - 1][y] = 0;
			arr[x][y] = temp;
			x = x - 1;
			break;
		}
		//  记录的移动步骤出栈
		moveArr.remove(moveArr.size() - 1);
	}

	//  获取状态，这里把9个数字按顺序组成一个整数来代表状态
	//  方法不唯一，只要能区分九宫格状态就行
	private Integer getStatus() {
		int status = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				status = status * 10 + arr[i][j];
			}
		}
		return status;
	}

	//  搜索方法
	private boolean search(int direction) {
		//  如果能够朝该方向行走
		if (canMove(direction)) {
			//  往该方向移动
			move(direction);
			//  移动后的状态
			Integer status = getStatus();
			//  如果已经是胜利状态，返回true
			if (WIN_STATE.equals(status)) {
				return true;
			}
			//  如果是之前走过的状态了，返回false
			if (statusSet.contains(status)) {
				//  这一步走错了，回退
				moveBack(direction);
				return false;
			}
			//  将当前状态存入set
			statusSet.add(status);
			//  继续朝四个方向进行搜索
			boolean searchFourOk = search(right) || search(down)
					|| search(left) || search(up);
			if (searchFourOk) {
				return true;
			} else {
				//  这一步走错了，把它的记录去除
				moveBack(direction);
				return false;
			}
		}
		return false;
	}

	//  解题入口方法
	public boolean solve() {
		Integer status = getStatus();
		//  如果已经是胜利状态，返回true
		if (WIN_STATE.equals(status)) {
			return true;
		}
		//  初始状态先记录
		statusSet.add(status);
		//  朝4个方向进行搜索
		return search(right) || search(down) || search(left) || search(up);
	}

	
	// 打印当前华容道的状态
	public void print() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}

