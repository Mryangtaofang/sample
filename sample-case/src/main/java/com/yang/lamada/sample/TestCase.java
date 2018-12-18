package com.yang.lamada.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class TestCase {
	private Map<Integer,Integer> matchOldNewPropValueId(Set<Integer> saleAttrValueIds, Set<Integer> newSaleAttrValueIds) {
        Map<Integer, Integer> matchIdMap = new HashMap<>();
        Set<Integer> sameAttrs = Sets.newHashSet(newSaleAttrValueIds);
        sameAttrs.retainAll(saleAttrValueIds);
        for(Integer samePropValueId: sameAttrs){
            matchIdMap.put(samePropValueId, samePropValueId);
        }
        newSaleAttrValueIds.removeAll(sameAttrs);
        saleAttrValueIds.removeAll(sameAttrs);

        //可以进行随机匹配的数量
        int number = Math.min(saleAttrValueIds.size(), newSaleAttrValueIds.size());
        if(number <= 0){
            return matchIdMap;
        }

        int[] newValueIdArray = newSaleAttrValueIds.stream().mapToInt(Integer::valueOf).toArray();
        List<Integer> randomIds =  this.selectSomeNum(newValueIdArray, number);

        int len = randomIds.size();
        for (Integer valueId : saleAttrValueIds){
            if(len <= 0){
                break;
            }
            matchIdMap.put(valueId, randomIds.get(--len));
        }

        return matchIdMap;
    }

    /**
     * 从数组中随机选出k个数
     */
    public List<Integer> selectSomeNum(int[] array, int k){
        List<Integer> result = Lists.newArrayList();

        int len = array.length;
        Random random = new Random();

        for(int i=0; i < k; i++){
            int index = random.nextInt(len-i);
            result.add(array[index]);
            array[index] = array[len-1-i];
        }

        return result;
    }
   
   @Test
   public void testCase(){
	   Set<Integer> saleAttrValueIds = Sets.newHashSet(1,3);
	   Set<Integer> newSaleAttrValueIds = Sets.newHashSet(2,4);
	   Map<Integer,Integer> map = new TestCase().matchOldNewPropValueId(saleAttrValueIds,newSaleAttrValueIds);
	   System.out.println(map);
   }
}
