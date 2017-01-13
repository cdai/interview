package advanced.combinatorial.backtracking.dfs.lc329_longestincreasingpathinamatrix;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 *  nums = [
 *  [9,9,4],
 *  [6,6,8],
 *  [2,1,1]
 *  ]
 * Return 4. The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *  nums = [
 *  [3,4,5],
 *  [3,2,6],
 *  [2,2,1]
 *  ]
 * Return 4. The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestIncreasingPath(
                new int[][]{{9, 9, 4}, {6, 10, 8}, {2, 1, 1}}));
    }

    private int[][] memo;

    private int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++)
                max = Math.max(max, dfs(memo, matrix, i, j, Long.MIN_VALUE));
        return max;
    }

    private int dfs(int[][] memo, int[][] matrix, int x, int y, long pre) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) return 0;
        if (pre >= matrix[x][y]) return 0; // must put before memo check. extra visit cache is not necessary.
        if (memo[x][y] > 0) return memo[x][y];

        int max = 0;
        for (int[] d : dirs)
            max = Math.max(max, dfs(memo, matrix, x + d[0], y + d[1], matrix[x][y]) + 1);
        memo[x][y] = max;
        return max;
    }

    // My 2AC: due to memo, every cell is computed once, so O(MN) time
    // No need to mark or save visited cells, since sequence must be increasing
    public int longestIncreasingPath2(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        this.memo = new int[A.length][A[0].length];
        int max = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[i].length; j++)
                max = Math.max(max, dfs(A, i, j, Integer.MIN_VALUE));
        return max;
    }

    private int dfs(int[][] A, int i, int j, int prev) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length) return 0;
        if (A[i][j] <= prev) return 0;
        if (memo[i][j] > 0) return memo[i][j];

        int max = 1;
        for (int[] dir : dirs)
            max = Math.max(max, dfs(A, i + dir[0], j + dir[1], A[i][j]) + 1);
        memo[i][j] = max;
        return max;
    }

    // Totally wrong! Since path could move from 4 directions, not only left and top.
    public int longestIncreasingPath_dp(int[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;

        int[] dp = new int[A[0].length];
        dp[0] = 1;
        for (int i = 1; i < A[0].length; i++)
            dp[i] = (A[0][i] > A[0][i - 1]) ? dp[i - 1] + 1 : 1;

        int max = 0;
        for (int i = 1; i < A.length; i++) {
            dp[0] = (A[i][0] > A[i - 1][0]) ? dp[0] + 1 : 1;
            for (int j = 1; j < A[i].length; j++) {
                dp[j] = Math.max((A[i][j] > A[i][j - 1]) ? dp[j - 1] + 1 : 1,
                        (A[i][j] > A[i - 1][j]) ? dp[j] + 1 : 1);
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

}
