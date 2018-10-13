package com.yang.num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3)
            return result;
        //先排序
        Arrays.sort(nums);
        //将 a + b + c = 0 的问题，转换成 两数求和问题
        for(int i=0; i<nums.length; i++){
            int targetSum = - nums[i];
            
            //这里为什么是 excludeIndex + 1? 
            //原因是：如果在excludeIndex之前找，是没有意义的，就算找了那个start(在excludeIndex之前),
            //然而在之前循环里，start肯定作为excludeIndex肯定出现过，这就重复了。
            int start = i + 1;
            int end = nums.length-1;
            
            while(start < end){
                if(start == i){
                    start ++;continue;
                }

                if(end == i){
                    end--;continue;
                }

                int sum = nums[start] + nums[end];

                if(sum == targetSum){
                    List<Integer> newArr = Arrays.asList(nums[i],nums[start],nums[end]);
                    result.add(newArr);
   
                    //nums数组排序之后，可能有多个相同的数，比如 [-3,-3,1,1,2,2]
                    //i = 0, nums[i] = -3
                    //start = 2 num[start] = 1
                    //end = 5  num[end] = 3
                    
                    //start后移，相同的1不用在次计算
                    while (start < end && nums[start] == newArr.get(1)) start++;
                    //end前移，相同的2不用在次计算
                    while (start < end && nums[end] == newArr.get(2)) end--;
                }else if(sum < targetSum){
                    start++ ;
                }else{
                    end--;
                } 
            }
            
            while (i+1 < nums.length && nums[i] == nums[i+1]) i++;
        }
        
        return result;
    }
    
    
    public static void main(String[] args){

    }
}
