package com.yang.num;



public class BigNumberAdd {

	public static void main(String[] participants) {
		System.out.print(new BigNumberAdd().add("11111", "99999"));
	}
	

    public String add(String num_1, String num_2) {
        
        /**
         * 比如输入的两个数如下:
         * num_1 = 1234567
         * num_2 = 7
         * 
         * 第一步:将小的数高位补0
         * num_1 = 1234567
         * num_2 = 0000007
         */
		int len_1 = num_1.length();
		int len_2 = num_2.length();
		
		int maxLen = Math.max(len_1, len_2);
		int minLen = Math.min(len_1, len_2);
		StringBuilder bufTmp = new StringBuilder();
        
		for (int i = 0; i < maxLen - minLen; i++)
			bufTmp.append("0");
		
		if (len_1 == minLen)
			num_1 = bufTmp.append(num_1).toString();
		else
			num_2 = bufTmp.append(num_2).toString();
		
		bufTmp = new StringBuilder();
		
		int result;
		int sc = 0;
		
		//从低位开始运算
		for (int i = maxLen - 1; i >= 0; i--) {
			result = getInt(num_1.charAt(i)) + getInt(num_2.charAt(i)) + sc;
			sc = result / 10;
			result = result % 10;
			bufTmp.insert(0, result);
		}
		
		if (sc == 1) 
			bufTmp.insert(0, sc);
		
		return bufTmp.toString();
    }
    
    private int getInt(char inChar){
    	try {
    		return Integer.valueOf(inChar + "");
		} catch (NumberFormatException e) {
			System.out.println("输入不合法");
			throw new RuntimeException();
		}
    }
}
