package com.yang.leetcode.exhaustion;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane,
 * find the maximum number of points that lie on the same straight line.
 *
 * 解法：穷举
 *   需要两重循环，第一重循环遍历起始点a，第二重循环遍历剩余点b。
 *   a和b如果不重合，就可以确定一条直线。
 *   对于每个点a，构建 斜率->点数 的map。
 *   (1)b与a重合，以a起始的所有直线点数+1 (用dup统一相加)
 *   (2)b与a不重合，a与b确定的直线点数+1
 */
public class FindMaxPointLine {

    public int maxPoints(Point[] points) {
        if(points == null || points.length < 1)
            return 0;
        int pointNum = points.length;
        if(pointNum <=2)
            return pointNum;

        int result = 0; //在同一条线上的最多点数.

        for(int i=0 ; i < pointNum; i++){
            /**key为斜率，value为线上的点数*/
            Map<Double , Integer> map = new HashMap<Double , Integer>();
            Point start = points[i];
            int vtl = 1; //与start垂直的点数
            int dupPointNum = 1; //与start重复的点数
            for(int j=0;j < pointNum;j++){
                if(j == i) continue;
                //重合
                if(start.x == points[j].x && start.y == points[j].y){
                    dupPointNum++;continue;
                }
                int x_d = points[j].x  - start.x;
                int y_d = points[j].y  - start.y;
                //垂直
                if(x_d == 0)
                    vtl++;
                else {
                    double d_vtl = (double)y_d/x_d;
                    if(map.get(d_vtl) == null) map.put(d_vtl,1);
                    else map.put(d_vtl,map.get(d_vtl)+1);
                }
            }

            result = Math.max(vtl,result);
            //找到其中最大的数量
            for(Map.Entry<Double , Integer> entry :map.entrySet())
                result = Math.max(entry.getValue()+dupPointNum,result);
            //针对所有的点都是重合的情况
            result = Math.max(dupPointNum,result);
        }
        return result;
    }


   public class Point {
        int x,y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }


    @Test
    public void testCase(){
        Point[] points = new Point[3];
        points[0] = new Point(1,1);
        points[1] = new Point(1,1);
        points[2] = new Point(1,1);
        System.out.println(this.maxPoints(points));
    }

}
