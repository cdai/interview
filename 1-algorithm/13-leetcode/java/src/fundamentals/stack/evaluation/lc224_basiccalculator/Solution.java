package fundamentals.stack.evaluation.lc224_basiccalculator;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces. You may assume that the given expression is always valid.
 * Some examples:
 *  "1 + 1" = 2
 *  " 2-1 + 2 " = 3
 *  "(1+(4+5+2)-3)+(6+8)" = 23
 * Note: Do not use the eval built-in library function.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.calculate("2-1 + 2 "));
    }


    // Inspired by solution from leetcode discuss
    public int calculate1(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);                          // Always keep most recent sum at top
        for (int i = 0, sign = 1; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';    // Be aware of outer loop boundary and i++
                for (; i < s.length() - 1 && Character.isDigit(s.charAt(i + 1)); i++) {
                    num = num * 10 + (s.charAt(i + 1) - '0');
                }
                stack.push(stack.pop() + sign * num);
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(sign);
                stack.push(0);
                sign = 1;                       // Don't forget to reset
            } else if (s.charAt(i) == ')') {    // Update last sum = current sum * sign
                stack.push(stack.pop() * stack.pop() + stack.pop());
            } /* else whitespace*/
        }
        return stack.pop();
    }

}
