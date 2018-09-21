package com.yang.leetcode.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串.
 * --如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串
 * 
 * 在答案的子串中的字母在目标字符串中是否需要具有相同的顺序？
 *
 *	——不需要。
 *
 * 样例
 * 给出 source="AAOBECODEBANC",target = "ABC" 满足要求的解  "BANC"
 * 
 * 解法：
 * 	第一步：将target字符串映射到256个支付数组中
 * 	targetMap[0...A...B...C....256];
 *  targetMap[A] = 1;
 *  targetMap[B] = 1;
 *  targetMap[C] = 1;
 *  
 *  
 *  第二步：遍历 source
 *  
 *  		end
 *  		 |
 *   索引：            0 1 2 3 4 5 6 7 8 9 10 11 12
 *  source = A A D B E C O D E B A  N  C;
 *  		 |
 *  	    start
 *  Loop:
 *     0): 执行第1个循环
 *     		srcMap[A] = 1; //字符A计数+1
 *     		found = 1; //发现了A
 *  		start = end = 0;
 *     1): 执行第2个循环
 *     		srcMap[A] = 1,srcMap[D] = 1; //其他值不变
 *     ...
 *     
 *     3): 执行第4个循环
 *     		srcMap[A] = 2,srcMap[B] = 1; 
 *     		found = 2; //又发现了B
 *     ...
 *     
 *     5): 执行第6个循环
 *     		srcMap[A] = 2,srcMap[B] = 1,srcMap[C] = 1;
 *     		found = 2; //又发现了C
 *     
 *          # 到此为止发现了所有的元素
 *          
 *          (1) 处理1：
 *     		从上面可以看出，start应该指向索引1, end指向索引6;
 *     
 *     		代码解析：srcMap[source.charAt(start)] > targetMap[source.charAt(start)]
 *     
 *     		通过srcMap['A'] > targetMap['A'],说明source中到目前为止,A出现的次数比target多，
 *     		所以，start应该向后移，srcMap['A']--;
 *     
 *     		现在情况：srcMap[A] = 1,srcMap[B] = 1,srcMap[C] = 1;索引指向如下:
 *			   		end        i
 *			   		 |         |
 *			   索引：            0 1 2 3 4 5 6 7 8 9 10 11 12
 *			source = A A D B E C O D E B A  N  C;
 *			   		   |       
 * 			   	     start   
 *    
 *    		(2) 处理2：
 * 			代码继续：minLen 最开始为总长度（其实就是假设存在包含target的子串），
 * 				   现在新的长度为：newLen = i-start+1,
 * 			新的长度要比minLen长度小，说明这个子串更小，替换
 * 			minLen = newLen = 5
 *
 *			   		 first     i
 *			   		   |       |
 *			   索引：            0 1 2 3 4 5 6 7 8 9 10 11 12
 *			source = A A D B E C O D E B A  N  C;
 *			   		   |         |
 * 			   	     start      end
 * 
 * 
 * 			(3) 处理3:
 * 			主要是为继续寻找做准备
 * 			现在情况：
 * 				srcMap[A] = 0,srcMap[B] = 1,srcMap[C] = 1;
 *              found = 2 //表示只发现了B和C
 *
 *			   		 first     i
 *			   		   |       |
 *			   索引：            0 1 2 3 4 5 6 7 8 9 10 11 12
 *			source = A A D B E C O D E B A  N  C;
 *			   		     |       |
 * 			   	       start    end
 *	继续外部循环... 			
 */
public class MiniWindow {
	private static final int array_size = 256;
	
	public String minWindow(String source , String target) {
        if(StringUtils.isBlank(source) || StringUtils.isBlank(target))
            return "";
        
        //字符串出现的次数
        int[] targetMap = new int[array_size];
        
        for (char oneChar: target.toCharArray()) 
        	targetMap[oneChar]++;
        
        int[] srcMap = new int[array_size];
        
        int minLen = source.length();
        int start = 0;
        int first = -1, end = 0;
        
        // 在source中发现了target中元素的数目,
        int found = 0;  
        
        for (int i = 0; i < source.length(); i++) {
        	char srcChar = source.charAt(i);
            srcMap[srcChar]++;
            
            if(srcMap[srcChar] <= targetMap[srcChar])
                found++;
            
            //如果在source中，发现了target的所有元素
            if(found == target.length()){
                // 处理1：start后移，删除无用元素,比如A出现了两次，前面的A可能需要舍弃
                while (start<=i && srcMap[source.charAt(start)] > targetMap[source.charAt(start)]){
                    srcMap[source.charAt(start)]--;
                    start++;
                }
                // 处理2：如果比当前最小子串小，则更新
                int newLen = (i+1-start);
                if(newLen <= minLen){
                    minLen = newLen;
                    first = start;
                    end = i+1;
                }
                
                // 处理3:
                srcMap[source.charAt(start)]--;
                start++;
                found--;
            }
        }
        
        return (first == -1) ? "" : source.substring(first, end);
    }
	
	@Test
	public void testCase(){
		System.out.print(new MiniWindow().minWindow("AAABECODEBANC", "ABC"));
	}
	
}
