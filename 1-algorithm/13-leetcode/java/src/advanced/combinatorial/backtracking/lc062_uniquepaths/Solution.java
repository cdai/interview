package advanced.combinatorial.backtracking.lc062_uniquepaths;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
public class Solution {

    // Caveat: for problem that seems very simple
    //  Trap may be hidden in corner cases or implicit performance/space requirement
    //  So always open your eyes!

    public int uniquePaths(int m, int n) {
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
