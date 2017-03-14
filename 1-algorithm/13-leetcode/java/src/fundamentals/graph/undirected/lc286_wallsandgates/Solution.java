package fundamentals.graph.undirected.lc286_wallsandgates;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Fill each empty room with the distance to its nearest gate.
 * If it is impossible to reach a gate, it should be filled with INF. (Same as initial)
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {
            {INF, -1, 0, INF},
            {INF, INF, INF, -1},
            {INF, -1, INF, -1},
            {0, -1, INF, INF},
        };
        sol.wallsAndGates(grid);

        for (int[] row : grid)
            System.out.println(Arrays.toString(row));
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int m = rooms.length, n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) dfs(rooms, i, j, m, n, 0, dirs);
            }
        }
    }

    private void dfs(int[][] rooms, int x, int y, int m, int n, int dist, int[][] dirs) {
        if (x < 0 || x >= m || y < 0 || y >= n || rooms[x][y] < dist) return; // Key!
        rooms[x][y] = dist;
        for (int[] d : dirs) {
            dfs(rooms, x + d[0], y + d[1], m, n, dist + 1, dirs);
        }
    }

    // Multi-end BFS in O(MN) time.
    // Simplify by removing inner loop and dist counter
    public void wallsAndGates_mbfs(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> q = new LinkedList<>();
        int m = rooms.length, n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) q.offer(new int[]{i, j});
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int[] d : dirs) {
                int x = p[0] + d[0], y = p[1] + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n || rooms[x][y] != INF) continue;
                rooms[x][y] = rooms[p[0]][p[1]] + 1;
                q.offer(new int[]{x, y});
            }
        }
    }

    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGates1(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[i].length; j++)
                if (rooms[i][j] == 0)
                    dfs(rooms, i, j, 0);
    }

    private void dfs(int[][] rooms, int i, int j, int dist) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < dist)
            return;

        rooms[i][j] = dist;
        dfs(rooms, i - 1, j, dist + 1);
        dfs(rooms, i + 1, j, dist + 1);
        dfs(rooms, i, j - 1, dist + 1);
        dfs(rooms, i, j + 1, dist + 1);
    }

    public void wallsAndGates_bfs(int[][] rooms) {
        Queue<Pos> q = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++)
            for (int j = 0; j < rooms[i].length; j++)
                if (rooms[i][j] == 0) q.offer(new Pos(i, j));

        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int dist = 0; !q.isEmpty(); dist++) {
            for (int i = q.size(); i > 0; i--) {
                Pos p = q.poll();
                rooms[p.x][p.y] = dist;
                for (int[] d : dir) {
                    int x = p.x + d[0], y = p.y + d[1];
                    if (x >= 0 && x < rooms.length &&
                        y >= 0 && y < rooms[0].length &&
                        rooms[x][y] == INF)
                        q.offer(new Pos(x, y));
                }
            }
        }
    }

    private static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
