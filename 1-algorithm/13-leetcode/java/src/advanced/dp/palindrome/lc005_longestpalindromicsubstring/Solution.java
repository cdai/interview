package advanced.dp.palindrome.lc005_longestpalindromicsubstring;

import java.util.Arrays;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaabaaaa"));
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }

        // start(n): start point of longest palindrome from [0,n-1]
        // longest(n) means length of longest palindrome from [0,n-1]
        int[] start = new int[n + 1];
        int[] longest = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            longest[i] = 1;
        }

        // longest(n) = max(longest(n), 2*j(+1)), if [n-2*j(+1),n-1] is palindrome
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            // Odd length palindrome
            for (int j = 0; 0 <= i - j && i + j < n && c[i - j] == c[i + j]; j++) {
                int length = (2 * j + 1);
                int max = Math.max(longest[i - j], length);
                if (longest[i + j + 1] < max) {
                    longest[i + j + 1] = max;
                    start[i + j + 1] = (max == length) ? (i - j) : start[i - j];
                }
            }
            // Even length palindrome
            for (int j = 1; 0 <= i - j + 1 && i + j < n && c[i - j + 1] == c[i + j]; j++) {
                int length = (2 * j);
                int max = Math.max(longest[i - j + 1], length);
                if (longest[i + j + 1] < max) {
                    longest[i + j + 1] = max;
                    start[i + j + 1] = (max == length) ? (i - j + 1) : start[i - j + 1];
                }
            }
        }
        return s.substring(start[n], start[n] + longest[n]);
    }

    // Error: Case-2/3, "bananas" ask f[last], "aaabaaaa" ask last
    public String longestPalindrome2(String s) {
        int[] f = new int[s.length()];
        f[0] = 0;

        int start = 0, longest = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((f[i - 1] == i - 1) && (s.charAt(i - 1) == c)) {    // Case-1: cb,b=[01,1]
                f[i] = i - 1;
            } else {
                int j = i - 1;
                while (j >= f[i - 1] && s.charAt(j) == c) {
                    j--;
                }

                if (j < f[i - 1]) {                                 // Case-2: bbbbb,b=[00000,0]
                    f[i] = f[i - 1];
                } else {                                            // Case-3: bbcb,b=[0021,0]
                    int last = f[i - 1] - 1;
                    f[i] = (last >= 0 && s.charAt(last) == c) ? f[last] : i;
                }
            }

            if (longest < i - f[i] + 1) {
                start = f[i];
                longest = i - f[i] + 1;
            }
        }
        System.out.println(Arrays.toString(f));
        return s.substring(start, start + longest);
    }

}
