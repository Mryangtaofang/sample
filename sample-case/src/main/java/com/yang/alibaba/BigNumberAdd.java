package com.yang.alibaba;


public class BigNumberAdd {

	public static void main(String[] participants) {
		System.out.print(new BigNumberAdd().add("-112312", "3241234"));
	}
	

    public String add(String num_1, String num_2) {
        String str="";
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
		
		int temp_1, temp_2, result;
		
		int sc = 0;
		try {
			for (int i = maxLen - 1; i >= 0; i--) {
				temp_1 = Integer.valueOf(num_1.charAt(i) + "");
				temp_2 = Integer.valueOf(num_2.charAt(i) + "");
				result = temp_1 + temp_2 + sc;
				sc = result / 10;
				result = result % 10;
				bufTmp.append(result);
			}
		} catch (NumberFormatException e) {
			System.out.println("输入不合法");
			throw new RuntimeException();
		}
		if (sc == 1) 
			bufTmp.append(bufTmp);
		
		str = bufTmp.reverse().toString();
		
		return str;
    }
}
