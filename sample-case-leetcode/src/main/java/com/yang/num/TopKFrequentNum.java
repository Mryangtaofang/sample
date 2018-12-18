package com.yang.num;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFrequentNum {

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int n: nums) 
          count.put(n, count.getOrDefault(n, 0) + 1);
        
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));


        for (int n: count.keySet()) {
          heap.add(n);
          
          if (heap.size() > k)
            heap.poll();
        }

        List<Integer> top_k = new LinkedList<>();
        
        while (!heap.isEmpty())
          top_k.add(heap.poll());
        
        Collections.reverse(top_k);
        
        return top_k;
    }
}
