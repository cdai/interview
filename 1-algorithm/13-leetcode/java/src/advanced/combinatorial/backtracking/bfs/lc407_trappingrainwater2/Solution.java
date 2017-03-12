package advanced.combinatorial.backtracking.bfs.lc407_trappingrainwater2;

import org.junit.jupiter.api.Test;

/**
 */
public class Solution {

    @Test
    void test() {
        trapRainWater(new int[][]{
                {12,13,1,12},
                {13,4,13,12},
                {13,8,10,12},
                {12,13,12,12},
                {13,13,13,13}
        });
    }

    // Wrong!
    public int trapRainWater(int[][] height) {
        if (height.length == 0 || height[0].length == 0) return 0;
        int m = height.length, n = height[0].length;
        int[][] water = new int[m][n];
        for (int i = 1; i < m - 1; i++) {
            int h1 = 0, h2 = 0;
            for (int l = 0, r = n - 1; l < r;) {
                if (height[i][l] < height[i][r]) {
                    h1 = Math.max(h1, height[i][l]);
                    water[i][l] = h1 - height[i][l++];
                } else {
                    h2 = Math.max(h2, height[i][r]);
                    water[i][r] = h2 - height[i][r--];
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int h1 = 0, h2 = 0;
            for (int l = 0, r = m - 1; l < r;) {
                if (height[l][j] < height[r][j]) {
                    h1 = Math.max(h1, height[l][j]);
                    water[l][j] = Math.min(water[l][j], h1 - height[l++][j]);
                } else {
                    h2 = Math.max(h2, height[r][j]);
                    water[r][j] = Math.min(water[r][j], h2 - height[r--][j]);
                }
            }
        }
        int total = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                total += water[i][j];
            }
        }
        return total;
    }

}
