package advanced.datastructure.kmp.lc214_shortestpalindrome;

import java.util.Arrays;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 *  Given "aacecaaa", return "aaacecaaa".
 *  Given "abcd", return "dcbabcd".
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.shortestPalindrome("abcd"));
        System.out.println(sol.shortestPalindrome("aacecaaa"));

        System.out.println(Arrays.toString(sol.preprocess("accabb#bbacca".toCharArray())));
    }

    public String shortestPalindrome(String s) {
        String p = s + "#" + new StringBuilder(s).reverse().toString();
        int[] dfa = preprocess(p.toCharArray());
        String max = s.substring(dfa[dfa.length - 1]);
        return new StringBuilder(max).reverse().append(s).toString();
    }

    private int[] preprocess(char[] p) {
        int[] dfa = new int[p.length];
        for (int i = 1, j = 0; i < p.length; i++) {
            while (j > 0 && p[j] != p[i])
                j = dfa[j - 1];
            if (p[j] == p[i])
                j++;
            dfa[i] = j;
        }
        return dfa;
    }

    // O(N^2) time
    //      <-i
    // aacecaaa
    public String shortestPalindrome_bruteforce3(String s) {
        StringBuilder rev = new StringBuilder(s).reverse();
        for (int i = 0; i < rev.length(); i++) {
            if (s.startsWith(rev.substring(i))) {
                return rev.substring(0, i) + s;
            }
        }
        return s; // Never reach here
    }

    // Another brute force approach from stephan.
    // Check s="abcde" and rev="edcba", check when s.startsWith rev[i,len)
    // Namely, s[0,len-i) is a palindrome.
    public String shortestPalindrome_bruteforce(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        for (int i = 0; i < s.length(); i++)
            if (s.startsWith(rev.substring(i))) // s[0,len-i] == rev[i,len),
                return rev.substring(0, i) + s;
        return s; // Never reach here except empty string
    }

    // Brute force approach: O(N^2)
    public String shortestPalindrome_bruteforce2(String s) {
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
