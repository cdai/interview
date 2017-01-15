package advanced.combinatorial.backtracking.bfs.lc130_surroundedregions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 *  X X X X
 *  X O O X
 *  X X O X
 *  X O X X
 * After running your function, the board should be:
 *  X X X X
 *  X X X X
 *  X X X X
 *  X O X X
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().solve(new char[][]{
                "XOX".toCharArray(),
                "XOX".toCharArray(),
                "XOX".toCharArray(),
        });
    }

    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;

        // 1.Flip preserved O to #
        for (int i = 0; i < m; i++) {
            flip(board, m, n, i, 0);
            flip(board, m, n, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            flip(board, m, n, 0, j);
            flip(board, m, n, m - 1, j);
        }

        // 2.Flip inner O and restore #
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void flip(char[][] board, int m, int n, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') continue;
            board[i][j] = '#';
            for (int[] d : dirs) q.offer(new int[]{i + d[0], j + d[1]});
        }
    }

    // TLE
    private void flip2(char[][] board, int m, int n, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] != 'O') return;
        board[x][y] = '#';
        for (int[] d : dirs) flip(board, m, n, x + d[0], y + d[1]);
    }

    // Flood fill algorithm (very lively): O(m*n) time
    // If we don't handle boundary-0 first, redundent DFS emerge, think about it!
    public void solve2(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        // 1.Flood fill all 'O' on the boundary
        for (int i = 0; i < board.length; i++) {
            floodFill(board, i, 0);         // Nice! Leave check to helper method!
            floodFill(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            floodFill(board, 0, i);
            floodFill(board, board.length - 1, i);
        }

        // 2.Flip all left 'O' to 'X' and recover flooded '#' to 'O'
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = (board[i][j] == '#') ? 'O' : 'X'; // Very smart!
    }

    private void floodFill(char[][] board, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{ i, j });
        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            i = pos[0];
            j = pos[1];
            if (board[i][j] != 'O') continue;

            board[i][j] = '#';
            if (i > 0) stack.push(new int[]{ i - 1, j });
            if (j > 0) stack.push(new int[]{ i, j - 1 });
            if (i < board.length - 1) stack.push(new int[]{ i + 1, j });
            if (j < board[i].length - 1) stack.push(new int[]{ i, j + 1 });
        }
    }

    // DFS will TLE for big image and test case here...
    private void floodFill_dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) return;
        if (board[i][j] != 'O') return;

        board[i][j] = '#';
        floodFill(board, i - 1, j);
        floodFill(board, i, j + 1);
        floodFill(board, i + 1, j);
        floodFill(board, i, j - 1);
    }

    // My 1AC
    public void solve1(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int[][] path = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                doSolveByBfs(board, path, i, j);
            }
        }

        // Restore
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '+') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void doSolveByBfs(char[][] board, int[][] path, int i, int j) {
        if (board[i][j] != 'O') {   //error2: no need to handle visited cells
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});

        // error1: Convert DFS to BFS for efficiency
        boolean isSurround = true;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            i = pos[0];
            j = pos[1];

            if (i < 0 || i > board.length-1 ||
                    j < 0 || j > board[i].length-1) {
                isSurround = false;
                break;
            }

            if (path[i][j] == 1 || board[i][j] == 'X') {
                //path[i][j] = 1;
                continue;
            }

            path[i][j] = 1;
            queue.offer(new int[] { i - 1, j });
            queue.offer(new int[] { i + 1, j });
            queue.offer(new int[] { i, j - 1 });
            queue.offer(new int[] { i, j + 1 });
        }

        for (i = 0; i < path.length; i++) {
            for (j = 0; j < path[i].length; j++) {
                if (path[i][j] == 0) {
                    continue;
                }
                if (isSurround) {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = '+';  //error2: no need to handle visited cells
                }
                path[i][j] = 0;
            }
        }
    }

    private boolean doSolveByDfs(char[][] board, int[][] path, int i, int j) {
        // Base-1: reach boundary, doesn't surrounded by X
        if (i < 0 || i > board.length-1 ||
                j < 0 || j > board[i].length-1) {
            return false;
        }

        // Base-2: reach X or visited cell
        if (board[i][j] == 'X' || path[i][j] == 1) {
            return true;
        }

        // Solve sub-problem:
        // pre-condition: i,j=valid and b[i][j]='O' and path[i][j]=0
        path[i][j] = 1;
        if (doSolveByDfs(board, path, i-1, j) && doSolveByDfs(board, path, i+1, j) &&
                doSolveByDfs(board, path, i, j-1) && doSolveByDfs(board, path, i, j+1)) {
            board[i][j] = 'X';
            path[i][j] = 0;
            return true;
        }
        path[i][j] = 0;
        return false;
    }

}
