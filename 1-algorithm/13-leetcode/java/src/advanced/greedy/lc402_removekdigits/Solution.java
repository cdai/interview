package advanced.greedy.lc402_removekdigits;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 */
public class Solution {

    // 1AC. Greedy O(N) time and space.
    public String removeKdigits(String num, int k) {
        if (num.isEmpty() || num.length() <= k) return "0";
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            for (; !s.isEmpty() && k > 0 && s.peek() > c; k--) {
                s.pop();
            }
            s.push(c);
        }

        // Remove leading zero by iterating stack from bottom (note k > 0 case)
        StringBuilder ret = new StringBuilder();
        for (Character c : s) {
            if (c == '0' && ret.length() == 0) continue;
            else ret.append(c);
        }
        return ret.length() == 0 ? "0" : ret.substring(0, ret.length() - k);
    }

    public String removeKdigits_my(String num, int k) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            for (; !s.isEmpty() && k > 0 && s.peek() > c; k--) {
                s.pop();
            }
            s.push(c);
        }

        while (!s.isEmpty() && k-- > 0) { // eg.112, k=1
            s.pop();
        }

        StringBuilder ret = new StringBuilder();
        for (Character c : s) { // iterate from bottom
            if (c == '0' && ret.length() == 0) continue;
            else ret.append(c);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }

}
