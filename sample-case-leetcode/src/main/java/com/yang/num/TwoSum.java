package com.yang.num;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2)
            return null;
        
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0 ; i<nums.length ;i++)
            map.put(nums[i],i);
        
        int[] result = new int[2];
        result[0] = result[1] = -1;
        
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                int otherIndex = map.get((target-nums[i]));
                if(otherIndex != i){
                    result[0] = i;
                    result[1] = otherIndex;
                    break;
                }
            }
        }
        
        return result;
    }
}
