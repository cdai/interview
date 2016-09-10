package advanced.scan.trend.lc085_maximalrectangle;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maximalRectangle(new char[][]{
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray(),
        }));
        System.out.println(new Solution().maximalRectangle(new char[][]{
                "1".toCharArray()
        }));
    }

    // Consider each level of matrix as a histogram! O(N^2) time.
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[] height = new int[matrix[0].length + 1];
        int max = 0;
        for (char[] row : matrix) {
            Stack<Integer> idx = new Stack<>();
            for (int i = 0; i <= row.length; i++) {
                if (i < row.length) height[i] = (row[i] == '1') ? height[i] + 1 : 0;
                while (!idx.isEmpty() && height[i] < height[idx.peek()]) // Very nice! height is N+1 length
                    max = Math.max(max, height[idx.pop()] * (idx.isEmpty() ? i : (i - 1 - idx.peek())));
                idx.push(i);
            }
        }
        return max;
    }

    // ["11"] wrong!
    public int maximalRectangle_wrong(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][][] dp = new int[m + 1][n + 1][2];

        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = new int[2];
                if (matrix[i - 1][j - 1] == '0') continue;
                dp[i][j][0] = Math.min(dp[i - 1][j][0], dp[i - 1][j - 1][0]) + 1;
                dp[i][j][1] = Math.min(dp[i][j - 1][1], dp[i - 1][j - 1][1]) + 1;
                max = Math.max(max, dp[i][j][0] * dp[i][j][1]);
            }
        }
        return max;
    }

}
