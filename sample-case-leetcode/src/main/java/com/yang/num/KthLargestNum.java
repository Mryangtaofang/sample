package com.yang.num;

import org.junit.Test;

public class KthLargestNum {
	
	//if array = [5,4,3,2,1] , k = 3
    public int findKthLargest(int[] nums, int k) {
    	if(k > nums.length)
    		return -1;
    	
    	//create a min heap use nums's first k num
    	//array will be [3,4,5,2,1]
    	//heap array is [3,4,5]
    	initHeap(nums,k);
    	
        
        for(int i=k; i<nums.length; i++){
            if(nums[i] > nums[0]){
                swap(nums, 0, i);
                adjustHeap(nums, 0, k);
            }
        }
        
        return nums[0];
    }
    
	public void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
    
    /**
     * create a min heap
     */
    private void initHeap(int[] nums,int heapLen){
        int mid = heapLen/2-1;
        
		for (int i = mid; i >= 0; i--) 
			adjustHeap(nums, i, heapLen);
    }
    
    
	public void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = i*2+1; k < length; k = k*2+1) {
			if (k + 1 < length && arr[k] > arr[k + 1])
				k++;
			
			if(arr[k] > temp)
				break;
			
			arr[i] = arr[k];
			i = k;
		}
		
		//[0.00464562,-0.00641432,0.00169747,-0.00166424,-0.00843738,0.00198920,-0.00933049,-0.00019338,-0.00667636,-0.00507468,0.00042527,-0.00334142,0.00602817,-0.00562309,-0.00253102,0.00823832,0.00281651,-0.00197313,0.00584621,-0.00643402,0.00503097,0.00054496,0.00958537,0.00999107,0.00809495,0.00597159,-0.00079630,-0.00817137,-0.00355676,0.00161580,0.00062111]

		arr[i] = temp;
	}
	
	@Test
	public void testCase(){
		new KthLargestNum().findKthLargest(new int[]{2,1}, 2);
	}
}
