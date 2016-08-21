package advanced.dp.twodim.lc064_minimumpathsum;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right
 * which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
public class Solution {

    // My 2AC: O(N) time and O(N) space
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 1.Init rolling DP state array
        int[] sum = new int[grid[0].length];
        sum[0] = grid[0][0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + grid[0][i];
        }

        // 2.Compute state of each row
        for (int i = 1; i < grid.length; i++) {
            sum[0] += grid[i][0];
            for (int j = 1; j < grid[i].length; j++) {
                sum[j] = Math.min(sum[j - 1], sum[j]) + grid[i][j];
            }
        }
        return sum[sum.length - 1];
    }

    // My 1AC
    public int minPathSum1(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // State fn(i,j) means: smallest sum along path from [0,0] to [i,j] (inclusive)
        // State transfer: fn(i,j) = min(fn(i-1,j), fn(i,j-1)) + a[i,j]

        int[][] fn = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i > 0 && j > 0) {
                    fn[i][j] = Math.min(fn[i-1][j], fn[i][j-1]) + grid[i][j];
                } else if (i > 0) {
                    fn[i][j] = fn[i-1][j] + grid[i][j];
                } else if (j > 0) {
                    fn[i][j] = fn[i][j-1] + grid[i][j];
                } else {
                    fn[i][j] = grid[0][0];
                }
            }
        }
        return fn[fn.length-1][fn[0].length-1];
    }

}
