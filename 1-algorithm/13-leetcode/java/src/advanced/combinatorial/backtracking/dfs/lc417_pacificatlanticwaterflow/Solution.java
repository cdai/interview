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
