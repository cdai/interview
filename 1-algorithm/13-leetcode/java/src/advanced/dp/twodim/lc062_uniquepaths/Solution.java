package advanced.dp.twodim.lc062_uniquepaths;

import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
public class Solution {

    // 3AC. O(N^2) space.
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 1; /* i=0 or j=0 or i=j=0 */
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // My 2AC: O(N^2) time and O(N) space
    public int uniquePaths2(int m, int n) {
        if (m <= 0 || n <= 0) {
            return -1;
        }
        int[] paths = new int[n];
        Arrays.fill(paths, 1);

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[j] += paths[j - 1];
            }
        }
        return paths[n - 1];
    }

    // Caveat: for problem that seems very simple
    //  Trap may be hidden in corner cases or implicit performance/space requirement
    //  So always open your eyes!

    // My 1AC: backtrack is overkill
    public int uniquePaths1(int m, int n) {
        int[][] cache = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cache[i][j] = -1;
            }
        }
        return uniquePaths(cache, m, n, 0, 0);
    }

    private int uniquePaths(int[][] cache, int m, int n, int i, int j) {
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        if (i >= m || j >= n) {
            return 0;
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        cache[i][j] = uniquePaths(cache, m, n, i + 1, j) + uniquePaths(cache, m, n, i, j + 1);
        return cache[i][j];
    }

}
