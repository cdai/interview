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

    // Overkill union find solution.
    // 1) k = i * n + j (not i*m + j, not i * j)
    // 2) i = k / n, j = k % n (not m !!!)
    public int numIslands(char[][] friends) {
        if (friends.length == 0) return 0;
        int m = friends.length, n = friends[0].length;

        // 1.Make each person as an individual circle initially
        int cnt = 0;
        int[] circles = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (friends[i][j] == '1') {
                    cnt++;
                    int pos = i * n + j;
                    circles[pos] = pos; // note [0,0] belong to 0, so don't use !=0 to decide
                }
            }
        }

        // 2.Union friends in transitive manner and decrement the counter
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < circles.length; i++) {
            int x1 = i / n, y1 = i % n;
            if (friends[x1][y1] != '1') continue;
            for (int[] d : dirs) { // try on 4 directions
                int x2 = x1 + d[0], y2 = y1 + d[1];
                if (x2 < 0 || x2 >= m || y2 < 0 || y2 >= n || friends[x2][y2] != '1') continue;
                int cir1 = find(circles, i);
                int cir2 = find(circles, x2 * n + y2);
                if (union(circles, cir1, cir2)) cnt--;
            }
        }
        return cnt;
    }

    private static boolean union(int[] circles, int c1, int c2) {
        if (c1 == c2) return false;
        circles[c1] = c2;
        return true;
    }

    // Find representative of current person, meanwhile compress path
    private static int find(int[] circles, int cur) {
        int par = circles[cur];
        if (cur != par) circles[cur] = find(circles, par);
        return circles[cur];
    }

    // My 3AC.
    // My 2AC: O(N^2) time, (why O(N) space?)
    public int numIslands3(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int islands = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfsMark(grid, i, j, m, n);
                    islands++;
                }
            }
        }
        return islands;
    }

    private void dfsMark(char[][] grid, int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1') return;
        grid[x][y] = 'x';
        dfsMark(grid, x + 1, y, m, n);
        dfsMark(grid, x - 1, y, m, n);
        dfsMark(grid, x, y + 1, m, n);
        dfsMark(grid, x, y - 1, m, n);
    }

    // My 1AC: not clear enough...
    public int numIslands1(char[][] grid) {
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
    public int numIslands12(char[][] grid) {
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
