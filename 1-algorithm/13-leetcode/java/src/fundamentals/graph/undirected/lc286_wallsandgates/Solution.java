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

    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGates(int[][] rooms) {
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
