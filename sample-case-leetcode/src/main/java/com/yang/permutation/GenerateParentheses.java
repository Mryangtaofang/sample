package com.yang.permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yangyaming
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    public void backtrack(List<String> ans, StringBuilder tmp, int open, int close, int max){
        if (tmp.length() == max * 2) {
            ans.add(tmp.toString());
            return;
        }

        if (open < max)
            backtrack(ans, tmp.append("("), open+1, close, max);
        
        if (close < open)
            backtrack(ans, tmp.append(")"), open, close+1, max);
    }
}
