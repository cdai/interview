package advanced.dp.twodim.lc115_distinctsubsequence;

import java.util.Arrays;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.numDistinct("rababbit", "rabit"));
    }

    // Note: dp[i,j-1] is no use
    //       r  a  b  i  t
    //   [1, 0, 0, 0, 0, 0]
    // r [1, 1, 0, 0, 0, 0]
    // a [1, 1, 1, 0, 0, 0]
    // b [1, 1, 1, 1, 0, 0]
    // a [1, 1, 2, 1, 0, 0]
    // b [1, 1, 2, 3, 0, 0]
    // b [1, 1, 2, 5, 0, 0]
    // i [1, 1, 2, 5, 5, 0]
    // t [1, 1, 2, 5, 5, 5]
    public int numDistinct(String S, String T) {
        int m = S.length(), n = T.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = 1; // base case: s, t="", #subseq=1

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j]; // s[0,i)-t[0,j-1) is no use. without t[j-1], we never know!
                if (S.charAt(i - 1) == T.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
        print(dp);
        return dp[m][n];
    }

    private void print(int[][] dp) {
        for (int[] row : dp)
            System.out.println(Arrays.toString(row));
    }

}
