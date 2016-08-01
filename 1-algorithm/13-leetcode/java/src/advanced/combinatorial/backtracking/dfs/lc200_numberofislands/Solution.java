package advanced.combinatorial.backtracking.dfs.lc200_numberofislands;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 *  11110
 *  11010
 *  11000
 *  00000
 *  Answer: 1
 * Example 2:
 *  11000
 *  11000
 *  00100
 *  00011
 *  Answer: 3
 */
public class Solution {

    public int numIslands(char[][] grid) {
        // Claim each island by marking number (start from 2 to make a difference from default 1)
        int seq = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    seq++;
                    claimIsland(grid, i, j, (char) ('0' + seq));
                }
            }
        }
        return (seq - 2);
    }

    private void claimIsland(char[][] grid, int i, int j, char seq) {
        // Go up
        if (i > 0 && grid[i - 1][j] == '1') {
            grid[i - 1][j] = seq;
            claimIsland(grid, i - 1, j, seq);
        }
        // Go right
        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') {
            grid[i][j + 1] = seq;
            claimIsland(grid, i, j + 1, seq);
        }
        // Go down
        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            grid[i + 1][j] = seq;
            claimIsland(grid, i + 1, j, seq);
        }
        // Go left
        if (j > 0 && grid[i][j - 1] == '1') {
            grid[i][j - 1] = seq;
            claimIsland(grid, i, j - 1, seq);
        }
    }

    // ["111","010","111"]
    public int numIslands2(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        //boolean[][] connect = new boolean[grid.length][grid[0].length];
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    char up = (i > 0) ? grid[i - 1][j] : '0';
                    char left = (j > 0) ? grid[i][j - 1] : '0';
                    if (up == '0' && left == '0') {
                        island += 1;
                    }
                }
            }
        }
        return island;
    }

}
