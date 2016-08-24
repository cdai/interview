package fundamentals.stack.evaluation.lc227_basiccalculator2;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces.
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * Some examples:
 *  "3+2*2" = 7
 *  " 3/2 " = 1
 *  " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("1 0 + 2 * 3 - 1 / 1 + 3"));
        System.out.println(new Solution().calculate("3+5 / 2"));
    }

    // My 2AC
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0, num = 0, op = '+'; i < s.length(); i++) {   // op is last operator
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ("+-*/".indexOf(c) >= 0 || i == s.length() - 1) {    // must be 'if' or i=len-1 won't reach here
                if ("*/".indexOf(op) >= 0)                          // subtract top before mul/div
                    result -= stack.peek();
                switch (op) {
                    case '+': stack.push(num); break;
                    case '-': stack.push(-num); break;
                    case '*': stack.push(stack.pop() * num); break; // only non-negative int, impossible '2*-1'
                    case '/': stack.push(stack.pop() / num); break;
                }
                num = 0;
                op = c;
                result += stack.peek();
            } /* else whitespace */
        }
        return result;
    }

    // My 1AC
    public int calculate1(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else {
                doCalculate(stack, num, sign);
                num = 0;
                sign = c;
            }
        }
        doCalculate(stack, num, sign);      // This is the key!!! Don't forget to handle last operator and number!

        int result = 0;
        for (int n : stack) {
            result += n;
        }
        return result;
    }

    private void doCalculate(Stack<Integer> stack, int num, char sign) {
        switch (sign) {
            case '+': stack.push(num); break;
            case '-': stack.push(-num); break;
            case '*': stack.push(stack.pop() * num); break;
            case '/': stack.push(stack.pop() / num); break;
            default: break;
        }
    }

    // This version is correct now, but TLE...
    // "1 0 + 2 * 3 - 1 / 1 + 3": Wrong when evaluating 2*3-1/1+3=2
    public int calculate2(String s) {
        // Perform + last
        int sub = 0, mulDiv = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+') {
                return calculate2(s.substring(0, i)) + calculate2(s.substring(i + 1));
            } else if (c == '-') {
                sub = i;
            } else if (c == '*' || c == '/') {
                mulDiv = i;
            }
        }

        // Perform -
        if (sub > 0) {
            return calculate2(s.substring(0, sub)) - calculate2(s.substring(sub + 1));
        }

        // Perform */ first
        if (mulDiv > 0) {
            int val1 = calculate2(s.substring(0, mulDiv));
            int val2 = calculate2(s.substring(mulDiv + 1));
            switch (s.charAt(mulDiv)) {
                case '*': return val1 * val2;
                case '/': return val1 / val2;
            }
        }

        StringBuilder num = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                num.append(c);
            }
        }
        return Integer.valueOf(num.toString());
    }

}
