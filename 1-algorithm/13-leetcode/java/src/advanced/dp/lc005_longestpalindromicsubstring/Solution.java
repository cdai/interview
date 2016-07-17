package advanced.dp.lc005_longestpalindromicsubstring;

import java.util.Arrays;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("aaabaaaa"));
    }

    // Error: Case-2/3, "bananas" ask f[last], "aaabaaaa" ask last
    public String longestPalindrome(String s) {
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
