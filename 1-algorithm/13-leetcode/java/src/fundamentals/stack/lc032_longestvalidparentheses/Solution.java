package fundamentals.stack.lc032_longestvalidparentheses;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestValidParentheses(")()())"));
    }

    // Smart solution to push index on stack!
    // "The idea is simple, we only update the result (max) when we find a "pair".
    // If we find a pair. We throw this pair away and see how big the gap is between current and previous invalid.
    // The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases."
    public int longestValidParentheses_stack(String s) {
        Deque<Integer> stack = new LinkedList<>(); // error: TLE if using Stack
        stack.push(-1); // sentinel
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                max = Math.max(max, i - stack.peek()); // actually, it's: i-(peek+1)+1, since peek+1 is the first valid char
            } else
                stack.push(i);
        }
        return max;
    }

    // DP solution: when matched ...dp[a],(,...dp[i-1],)
    // dp[i] = dp[i - 1] + 2 + dp[before matched (]
    public int longestValidParentheses(String s) {
        int max = 0, opened = 0; // essentially, this is a stack!
        int[] dp = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == ')') {
                if (--opened >= 0) {
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += dp[i - dp[i]];
                    max = Math.max(max, dp[i]);
                } else opened = 0;
            } else opened++;
        }
        return max;
    }

    // "()(()" wrong because of the middle "("
    public int longestValidParentheses_wrong(String s) {
        int stack = 0, cnt = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack++;
            } else {
                stack--;
                if (stack < 0) {
                    stack = 0;
                    max = Math.max(max, cnt);
                    cnt = 0;
                } else
                    cnt += 2;
            }
        }
        return Math.max(max, cnt);
    }

    // My 1AC
    public int longestValidParentheses1(String s) {
        if (s.length() == 0) {
            return 0;
        }

        // fn[i] means: from i to fn[i] are valid parentheses
        // transfer: fn[i] = i-1 (if fn[i-1]=='(') or fn[i-2] (if fn[i-2] > -1)
        int[] fn = new int[s.length()];
        fn[0] = (s.charAt(0) == '(') ? 1 : -1;

        int longest = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                fn[i] = i + 1; // next will get: last = i + 1 - 1 = i
            } else {
                int last = fn[i - 1] - 1;
                if (last >= 0 && s.charAt(last) == '(') {
                    fn[i] = (last - 1 >= 0 && fn[last - 1] >= 0) ? fn[last - 1] : last;
                    longest = Math.max(longest, i - fn[i] + 1);
                } else {
                    fn[i] = -1;
                }
            }
        }
        return longest;
    }

}
