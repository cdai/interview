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
        System.out.println(sol.calculate("2-1 + 2 "));
    }

    // Num: read ahead or each digit by each
    // Sum: use var or store at the top of stack or sum upon ')'

    // Nice solution: think parenthesis as context
    // Only store sign of current context on stack
    // Start from +1 sign and scan s from left to right;
    //  3.if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
    //  4.if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
    //  5.if c == '(': Push this context sign to stack;
    //  6.if c == ')': Pop this context and we come back to last context;
    //  7.Add the last num. This is because we only add number after '+' / '-'.
    public int calculate(String str) {
        Stack<Integer> s = new Stack<>();
        int sum = 0, num = 0, sign = 1;
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) num = num * 10 + (c - '0');
            else if (c == '(') s.push(sign);
            else if (c == ')') s.pop();
            else if (c == '+' || c == '-') {
                sum += sign * num;
                num = 0;
                sign = (s.isEmpty() ? 1 : s.peek()) * (c == '+' ? 1 : -1);
            }
        }
        sum += sign * num;
        return sum;
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
