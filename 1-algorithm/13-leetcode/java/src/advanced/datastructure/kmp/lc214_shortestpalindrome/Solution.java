package advanced.datastructure.kmp.lc214_shortestpalindrome;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 *  Given "aacecaaa", return "aaacecaaa".
 *  Given "abcd", return "dcbabcd".
 */
public class Solution {

    // Brute force approach: O(N^2)
    public String shortestPalindrome(String s) {
        if (s.isEmpty()) return s;
        StringBuilder ret = new StringBuilder();
        for (int i = (s.length() - 1) / 2; i >= 0; i--) {
            if (extend(s, i, i + 1)) // Even
                return ret.append(s.substring(2 * (i + 1))).reverse().append(s).toString();
            if (extend(s, i - 1, i + 1)) // Odd
                return ret.append(s.substring(2 * i + 1)).reverse().append(s).toString();
        }
        return ret.append(s.substring(1)).reverse().append(s).toString();
    }

    private boolean extend(String s, int i, int j) {
        if (i < 0 || j >= s.length()) return false;
        for (; i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j); i--, j++);
        return i < 0;
    }

}
