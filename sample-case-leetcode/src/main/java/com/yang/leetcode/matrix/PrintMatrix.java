package com.yang.leetcode.matrix;

import java.util.ArrayList;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下矩阵： 
 * 1 2
 * 3 4
 * 1  2  4 
 * 5  6  8
 * 9  10 12 
 * 13 14 16
 * 则依次打印出数字
 * 1, 2, 3, 4,
 * 8, 12,16,15,
 * 14,13,9, 5,
 * 6, 7, 11,10.
 *
 */
public class PrintMatrix {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0){
        	return null;
        }
        int xEndIndex,yEndIndex,xStartIndex,yStartIndex;
        int y,x;
        xEndIndex = matrix[0].length-1;
        yEndIndex  = matrix.length-1;
        yStartIndex = xStartIndex = x = y= 0;
        
        ArrayList<Integer> result = new ArrayList<Integer>();
		do{
			for (;x <= xEndIndex; x++) { //右
				result.add(matrix[y][x]);
			}
			yStartIndex++; y++;x--;
			
			if(yStartIndex > yEndIndex) break;
			
			for (;y <= yEndIndex; y++) { //下
				result.add(matrix[y][x]);
			}
			xEndIndex--;x--;y--;
			
			if(xStartIndex > xEndIndex) break;
			
			for (;x >= xStartIndex; x--) {//左
				result.add(matrix[y][x]);
			}
			yEndIndex--;y--;x++;
			
			if(yStartIndex > yEndIndex) break;
			
			for (; y >= yStartIndex; y--) {//上
				result.add(matrix[y][x]);
			}
			xStartIndex++; x++;y++;
			
			if(xStartIndex > xEndIndex) break;
			
		}while(true);

        return result;
    }
}


