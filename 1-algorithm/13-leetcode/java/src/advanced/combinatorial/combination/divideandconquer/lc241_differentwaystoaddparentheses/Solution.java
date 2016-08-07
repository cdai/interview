package advanced.combinatorial.combination.divideandconquer.lc241_differentwaystoaddparentheses;

import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ('0' <= c && c <= '9') {
                continue;
            }

            // Pick operator at char[i]
            List<Integer> left = diffWaysToCompute(input.substring(0, i));
            List<Integer> right = diffWaysToCompute(input.substring(i + 1));

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
