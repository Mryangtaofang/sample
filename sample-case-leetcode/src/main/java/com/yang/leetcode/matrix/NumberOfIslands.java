package com.yang.leetcode.matrix;

import org.junit.Test;

/**
 * https://leetcode.com/problems/number-of-islands/
 * @author yangyaming
 */
public class NumberOfIslands {

	
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        
        int yLen = grid.length;
        int xLen = grid[0].length;
        
        int count = 0;
        for(int i=0; i<yLen ;i++)
            for(int j=0;j<xLen ;j++)
                if(grid[i][j] == '1'){
                    count++;
                    //深度优先遍历
                    dfsSearch(grid,i,j);
                }
        
        return count;
    }
    
    private void dfsSearch(char[][] grid,int y,int x){
        if(y >= grid.length || x >= grid[0].length || x<0 || y<0)
            return;
        
        if(grid[y][x] == '0')
            return;
        
        grid[y][x] = '0';
        
        dfsSearch(grid,y-1,x);
        dfsSearch(grid,y+1,x);
        dfsSearch(grid,y,x-1);
        dfsSearch(grid,y,x+1);
    }
    
    @Test
    public void testCase(){
    	new NumberOfIslands().numIslands(new char[][]{{'1','0','1','1','0','1','1'}});
    }
}
