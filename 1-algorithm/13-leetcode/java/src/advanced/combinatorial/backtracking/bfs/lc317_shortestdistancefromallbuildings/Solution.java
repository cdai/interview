package advanced.combinatorial.backtracking.bfs.lc317_shortestdistancefromallbuildings;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely. Each 1 marks a building which you cannot pass through. Each 2 marks an obstacle which you cannot pass through. For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 * Note: There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.shortestDistance(new int[][]{
                {1, 0, 2, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0}
        }));
    }

    // Accumulate distance from building one by one. But how to distinguish node reachable from all buildings?
    // Mark cell to -1,-2... after each BFS complete and only walk onto those reachable from all previous buildings.
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        int min = -1;
        for (int i = 0, walk = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    min = bfs(grid, dist, i, j, walk--);
        return min;
    }

    private int bfs(int[][] grid, int[][] dist, int row, int col, int walk) {
        int m = grid.length, n = grid[0].length;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int min = -1;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        for (int step = 1; !q.isEmpty(); step++) {
            for (int i = q.size(); i > 0; i--) {
                Pair p = q.poll();
                for (int[] d : dir) {
                    int x = p.x + d[0], y = p.y + d[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != walk) continue;

                    grid[x][y]--;
                    dist[x][y] += step;
                    if (min == -1 || min > dist[x][y])
                        min = dist[x][y];
                    q.offer(new Pair(x, y));
                }
            }
        }
        return min;
    }

    class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
