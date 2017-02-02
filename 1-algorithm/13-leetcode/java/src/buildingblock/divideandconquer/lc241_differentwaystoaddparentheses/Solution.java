package buildingblock.divideandconquer.lc241_differentwaystoaddparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string of numbers and operators, return all possible results
 * from computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 * Example 1:
 *  Input: "2-1-1".
 *  ((2-1)-1) = 0
 *  (2-(1-1)) = 2
 *  Output: [0, 2]
 * Example 2
 *  Input: "2*3-4*5"
 *  (2*(3-(4*5))) = -34
 *  ((2*3)-(4*5)) = -14
 *  ((2*(3-4))*5) = -10
 *  (2*((3-4)*5)) = -10
 *  (((2*3)-4)*5) = 10
 *  Output: [-34, -14, -10, -10, 10]
 */
public class Solution {

    // 3AC. Alternative
    public List<Integer> diffWaysToCompute3(String input) {
        List<String> expr = new ArrayList<>();
        for (int i = 0, j = 0; i <= input.length(); i++) {
            if (i == input.length()) expr.add(input.substring(j, i));
            else if("+-*".indexOf(input.charAt(i)) >= 0) {
                expr.add(input.substring(j, i));
                expr.add(input.substring(i, i + 1));
                j = i + 1;
            }
        }
        return compute(expr.toArray(new String[0]), 0, expr.size() - 1);
    }

    private List<Integer> compute(String[] expr, int start, int end) {
        if (start == end) return Arrays.asList(Integer.valueOf(expr[start]));
        List<Integer> ret = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (!"+-*".contains(expr[i])) continue;
            for (int left : compute(expr, start, i - 1)) {
                for (int right : compute(expr, i + 1, end)) {
                    if ("+".equals(expr[i])) ret.add(left + right);
                    else if ("-".equals(expr[i])) ret.add(left - right);
                    else ret.add(left * right);
                }
            }
        }
        return ret;
    }

    private Map<String,List<Integer>> memo = new HashMap<>();

    // Eg.2-1-1 -> 2-(1-1) It's ok! That's what this problem expect!
    // Different from Unique Path II, sub-solution of this problem should be cached
    public List<Integer> diffWaysToCompute(String input) {
        if (memo.containsKey(input)) {
            return memo.get(input);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) { // Suppose it's in well format
            if ("+-*".indexOf(input.charAt(i)) < 0) continue;
            List<Integer> lvals = diffWaysToCompute(input.substring(0, i));
            List<Integer> rvals = diffWaysToCompute(input.substring(i + 1));
            for (int lval : lvals) {
                for (int rval : rvals) {
                    switch (input.charAt(i)) {
                        case '+': result.add(lval + rval); break;
                        case '-': result.add(lval - rval); break;
                        case '*': result.add(lval * rval); break;
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.valueOf(input));
        }
        memo.put(input, result);
        return result;
    }

    // My 1AC
    public List<Integer> diffWaysToCompute1(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ('0' <= c && c <= '9') {
                continue;
            }

            // Pick operator at char[i]
            List<Integer> left = diffWaysToCompute1(input.substring(0, i));
            List<Integer> right = diffWaysToCompute1(input.substring(i + 1));

            // Generate all combinations
            for (int j = 0; j < left.size(); j++) {
                for (int k = 0; k < right.size(); k++) {
                    switch (c) {
                        case '+': result.add(left.get(j) + right.get(k)); break;
                        case '-': result.add(left.get(j) - right.get(k)); break;
                        case '*': result.add(left.get(j) * right.get(k)); break;
                    }
                }
            }
        }
        if (result.isEmpty()) { // no operator only number at this level
            result.add(Integer.valueOf(input));
        }
        return result;
    }

    //
    public List<Integer> diffWaysToCompute2(String input) {
        List<Integer> result = new ArrayList<>();
        doCompute(result, input, numberOfOperator(input));
        return result;
    }

    private void doCompute(List<Integer> result, String input, int k) {
        if (k == 0) {
            result.add(Integer.valueOf(input));
            return;
        }

        for (int i = 0; i < k; i++) {
            doCompute(result, evaluate(input, k), k - 1);
        }
    }

    private int numberOfOperator(String input) {
        int cnt = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                cnt++;
            }
        }
        return cnt;
    }

    private String evaluate(String input, int pair) {
        String num1, num2;
        char op;
        for (int i = 0, j = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (j == pair) {
                //...
            }
            if (c == '+' || c == '-' || c == '*') {
                j++;
                if (j == pair + 1) {
                    op = input.charAt(i);
                }
            }
        }
        return "";
    }

}
