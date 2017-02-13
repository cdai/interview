package advanced.dp.twodim.lc063_uniquepaths2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Follow up for "Unique Paths": Now consider if some obstacles are added to the grids. How many unique paths would there be? An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example, There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *  [
 *      [0,0,0],
 *      [0,1,0],
 *      [0,0,0]
 *  ]
 * The total number of unique paths is 2.
 * Note: m and n will be at most 100.
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(3, uniquePathsWithObstacles(new int[][]{
                {0,0}, {0,0}, {0,0}, {1,0}, {0,0}
        }));
    }

    // For first row and column, subsequent cell (#path) should be 0 if one obstalce appears
    // Case 1 - first row:      dp[j] set to 0 since dp[j] += dp[j-1]. one obstacle causes 0 for all the followings
    // Case 2 - first column:   dp[j] set to 0 since we never update j=0 again once it is set to 0 by obstacle
    // Case 3 - others:         dp[j] simply set to dp[j] + dp[j-1]
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int[] row : grid) {
            for (int j = 0; j < n; j++) {
                if (row[j] == 1) {
                    dp[j] = 0;
                } else {
                    if (j > 0) dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    // My 2AC: DP solution with O(N^2) time and O(N) space
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        // First column/row are special: if there is an obstacle, then path of following grid are all 0
        int[] paths = new int[obstacleGrid[0].length];
        paths[0] = (obstacleGrid[0][0] == 1) ? 0 : 1;
        for (int i = 1; i < obstacleGrid[0].length; i++) {
            paths[i] = (obstacleGrid[0][i] == 1 || paths[i - 1] == 0) ? 0 : 1;
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            paths[0] = (obstacleGrid[i][0] == 1 || paths[0] == 0) ? 0 : 1;
            for (int j = 1; j < obstacleGrid[i].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    paths[j] = 0;
                } else {
                    paths[j] += paths[j - 1];
                }
            }
        }
        return paths[paths.length - 1];
    }

    // My 1AC: same as I, backtracking is an overkill...
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) {
            return 0;
        }

        int[][] cache = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                cache[i][j] = -1;
            }
        }

        return uniquePath(cache, obstacleGrid, 0, 0);
    }

    private int uniquePath(int[][] cache, int[][] obstacleGrid, int i, int j) {
        if (i == obstacleGrid.length || j == obstacleGrid[i].length || obstacleGrid[i][j] == 1) { // error1: put first, otherwise error when [m][n]=1
            return 0;
        }

        if (i == obstacleGrid.length-1 && j == obstacleGrid[i].length-1) {
            return 1;
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        cache[i][j] = uniquePath(cache, obstacleGrid, i+1, j) + uniquePath(cache, obstacleGrid, i, j+1);
        return cache[i][j];
    }

}
