package advanced.combinatorial.backtracking.bfs.lc130_surroundedregions;

import java.util.LinkedList;
import java.util.Queue;

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

    public void solve(char[][] board) {
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
