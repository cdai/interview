package advanced.dp.lc063_uniquepaths2;

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

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
