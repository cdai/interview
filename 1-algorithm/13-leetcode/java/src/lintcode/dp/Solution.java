package lintcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestCommonSubstring("ABCD", "EABD"));
    }

    // 77-Longest Common Subsequence
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else if (dp[i - 1][j] >= dp[i][j - 1]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[m][n];
    }

    // 79-Longest Common Substring
    // dp[i][j] means the LCS ended with i and j
    // so don't consider dp[i-1][j] and dp[i][j-1]
    public int longestCommonSubstring(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) != B.charAt(j - 1)) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        dump(dp);
        return max;
    }

    // 92-Backpack
    @Test
    void testBackpack() {
        Assertions.assertEquals(6, backPack(7, new int[]{1, 3, 5})); // 1,5 not 1,3 or 3,5
    }

    // Since each item can be used once, we must treat each item as phase
    // [0, 1, 1, 1, 1, 1, 1, 1]
    // [0, 1, 1, 3, 4, 4, 4, 4]
    // [0, 1, 1, 3, 4, 5, 6, 6]
    public int backPack(int m, int[] items) {
        int[] dp = new int[m + 1];
        for (int it : items) {
            for (int sz = m; sz >= 0; sz--) {
                if (sz >= it) {
                    dp[sz] = Math.max(dp[sz], dp[sz - it] + it);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[m];
    }

    private void dump(int[][] dp) {
        for (int[] row : dp)
            System.out.println(Arrays.toString(row));
    }

}
