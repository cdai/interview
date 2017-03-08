package advanced.combinatorial.backtracking.dfs.lc417_pacificatlanticwaterflow;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    @Test
    void test() {
        pacificAtlantic(new int[][]{
                {3, 3, 3, 3, 3, 3},
                {3, 0, 3, 3, 0, 3},
                {3, 3, 3, 3, 3, 3}
        });
    }

    private static int PACIFIC = 0;
    private static int ATLANTIC = 1;

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        // Start from boundary to mark if internal cell are reachable from boundary
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] pac = new boolean[m][n], atl = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, m, n, Integer.MIN_VALUE, dirs, pac);    // reach Pacific from left boundary
            dfs(matrix, i, n - 1, m, n, Integer.MIN_VALUE, dirs, atl);// reach Atlantic from right boundary
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, 0, j, m, n, Integer.MIN_VALUE, dirs, pac);    // reach Pacific from top boundary
            dfs(matrix, m - 1, j, m, n, Integer.MIN_VALUE, dirs, atl);// reach Atlantic from bottom boundary
        }

        // Save result if some cell can flow into both Pacific and Atlantic
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) ret.add(new int[]{i, j});
            }
        }
        return ret;
    }

    private void dfs(int[][] matrix, int x, int y, int m, int n, Integer prev, int[][] dirs, boolean[][] reach) {
        if (x < 0 || x >= m || y < 0 || y >= n || prev > matrix[x][y] || reach[x][y]) return;
        reach[x][y] = true;
        for (int[] d : dirs) {
            dfs(matrix, x + d[0], y + d[1], m, n, matrix[x][y], dirs, reach);
        }
    }

    public List<int[]> pacificAtlantic_backtrack(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        Boolean[][][] memo = new Boolean[2][m][n];
        List<int[]> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(matrix, i, j, Integer.MAX_VALUE, PACIFIC, memo) &&
                        dfs(matrix, i, j, Integer.MAX_VALUE, ATLANTIC, memo)) {
                    ret.add(new int[]{i, j});
                }
            }
        }
        return ret;
    }

    private boolean dfs(int[][] matrix, int x, int y, Integer prev, int ocean, Boolean[][][] memo) {
        if (x < 0 || y < 0) return ocean == PACIFIC;
        if (x >= matrix.length || y >= matrix[x].length) return ocean == ATLANTIC;
        if (matrix[x][y] > prev) return false;
        if (memo[ocean][x][y] != null) return memo[ocean][x][y];

        prev = matrix[x][y];
        matrix[x][y] = Integer.MAX_VALUE;
        memo[ocean][x][y] = dfs(matrix, x - 1, y, prev, ocean, memo) ||
                dfs(matrix, x, y + 1, prev, ocean, memo) ||
                dfs(matrix, x + 1, y, prev, ocean, memo) ||
                dfs(matrix, x, y - 1, prev, ocean, memo);
        matrix[x][y] = prev;
        return memo[ocean][x][y];
    }

}
