package advanced.dp.lc032_longestvalidparentheses;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestValidParentheses(")()())"));
    }

    public int longestValidParentheses(String s) {
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
