package fundamentals.stack.lc150_evaluatereversepolishnotation;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 *  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 *  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class Solution {

    // My 2AC: O(N) time and O(N) space. Assume the expression is valid.
    // Illegal char "1a", bad format ["/", '1'], div-by-zero ["1","0","/"]
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String t : tokens) {
            switch (t) { // Java 8!
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int sub = stack.pop();
                    stack.push(stack.pop() - sub);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int div = stack.pop();
                    stack.push(stack.pop() / div);
                    break;
                default:
                    stack.push(Integer.valueOf(t));
                    break;
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

    // My 1AC: it's very dangerous to check '-' and '-1'. See nice string match in 2AC.
    public int evalRPN1(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // Must be number with/out sign
            if (token.length() > 1) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            // Could be operator or number
            char c = token.charAt(0);
            switch (c) {
                case '+': stack.push(stack.pop() + stack.pop()); break;
                case '*': stack.push(stack.pop() * stack.pop()); break;
                case '-':
                    int sub1 = stack.pop();
                    int sub2 = stack.pop();
                    stack.push(sub2 - sub1);
                    break;
                case '/':
                    int div1 = stack.pop();
                    int div2 = stack.pop();
                    stack.push(div2 / div1);
                    break;
                default: stack.push(Integer.valueOf(token)); break;
            }
        }
        return stack.isEmpty() ? 0 : stack.pop();
    }

}
