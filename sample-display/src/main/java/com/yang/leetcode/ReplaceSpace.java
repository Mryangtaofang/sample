package com.yang.leetcode;

/**
 * 替换空格
 * @author yangyaming
 */
public class ReplaceSpace {
	public static void main(String[] args){
		StringBuffer name = new StringBuffer("yang ya ming");
		System.out.println(new ReplaceSpace().replaceSpace(name));
	}
	
	//这个效率很低
//    public String replaceSpace(StringBuffer str) {
//    	if(str == null){
//    		return null;
//    	}
//    	int index = str.indexOf(" ");
//    	while(index >= 0){
//    		str = str.replace(index, index+1, "%20");
//    		index = str.indexOf(" ");
//    	}
//    	
//    	return str.toString();
//    }
	
	
	public String replaceSpace(StringBuffer str) {
		if (str == null) {
			return null;
		}
		
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<str.length();i++){
			char bufChar = str.charAt(i);
			if(bufChar == ' '){
				builder.append("%20");
			}else{
				builder.append(bufChar);
			}
		}
		return builder.toString();
	}
}
