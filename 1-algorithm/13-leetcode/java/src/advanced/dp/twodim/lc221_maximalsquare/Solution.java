package advanced.dp.twodim.lc221_maximalsquare;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's
 * and return its area.
 * For example, given the following matrix:
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1
 *  1 0 0 1 0
 * Return 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(
                new Solution().maximalSquare(new char[][]{
                        "10100".toCharArray(),
                        "10111".toCharArray(),
                        "11111".toCharArray(),
                        "10010".toCharArray()
                }));
    }

    // My 3AC. dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
    // Now in rolling dp: dp[i-1] -> dp[i-1][j], dp[i] -> dp[i][j-1], prev -> dp[i-1][j-1]
    public int maximalSquare(char[][] A) {
        if (A.length == 0 || A[0].length == 0) return 0;
        int[] dp = new int[A[0].length + 1];
        int max = 0, prev = 0;
        for (char[] row : A) {
            for (int i = 1; i <= row.length; i++) {
                int tmp = dp[i];
                if (row[i - 1] == '0') dp[i] = 0;
                else {
                    dp[i] = Math.min(prev, Math.min(dp[i - 1], dp[i])) + 1;
                    max = Math.max(max, dp[i]);
                }
                prev = tmp;
            }
        }
        return max * max;
    }

    // O(N^2) time. dp[i][j] denotes the max length of a square of 1's whose bottom-right is at i-1,j-1
    // Thus we save the trouble of boundary initialization.
    public int maximalSquare2(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[i - 1].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {  // error: '1' not 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    // My 1AC: No need to init boundary
    public int maximalSquare1(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) { // error2: error if extract m and n before this guard
            return 0;
        }

        // square[i][j] = max area length including i,j
        int[][] square = new int[matrix.length][matrix[0].length];
        int max = 0;

        // Optimize: avoid if in loop body
        for (int i = 0; i < matrix.length; i++) {
            square[i][0] = matrix[i][0] - '0';
            max = Math.max(max, square[i][0]);
        }
        for (int j = 0; j < matrix[0].length; j++) {
            square[0][j] = matrix[0][j] - '0';
            max = Math.max(max, square[0][j]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                int up = square[i - 1][j];
                int left = square[i][j - 1];
                int dia = square[i - 1][j - 1];
                int cur = matrix[i][j] - '0'; // error1: matrix is char, auto convert to int silently

                if (cur == 0) {
                    square[i][j] = 0;
                } else {
                    // Key!!! Max length at [i,j] is min of up,left,diagonal
                    square[i][j] = Math.min(Math.min(up, left), dia) + 1;
                    max = Math.max(max, square[i][j]);
                }
            }
        }
        return max * max;
    }

}
