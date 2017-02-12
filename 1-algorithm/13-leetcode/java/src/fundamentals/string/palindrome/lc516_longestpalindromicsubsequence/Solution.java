package fundamentals.string.palindrome.lc516_longestpalindromicsubsequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 */
public class Solution {

    //    d  b  c  c  a  b
    // d [1, 1, 1, 2, 2, 4]
    // b [0, 1, 1, 2, 2, 4]
    // c [0, 0, 1, 2, 2, 2]
    // c [0, 0, 0, 1, 1, 1]
    // a [0, 0, 0, 0, 1, 1]
    // b [0, 0, 0, 0, 0, 1]
    @Test
    void test() {
        Assertions.assertEquals(4, longestPalindromeSubseq("dbccab"));
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1; // Base case
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        print(dp);
        return dp[0][n - 1];
    }

    private void print(int[][] dp) {
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
    }

}
