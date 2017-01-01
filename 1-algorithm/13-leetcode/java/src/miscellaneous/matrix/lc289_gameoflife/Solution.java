package miscellaneous.matrix.lc289_gameoflife;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *  1.Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 *  2.Any live cell with two or three live neighbors lives on to the next generation.
 *  3.Any live cell with more than three live neighbors dies, as if by over-population..
 *  4.Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *  Write a function to compute the next state (after one update) of the board given its current state.
 * Follow up:
 *  1.Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 *  2.In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class Solution {

    // Exploit every bit: save state of next gen in higher bit of each cell,since it could either be 0 or 1 only.
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = -(board[i][j] & 1); // subtract 1 first if A[i,j]=1
                for (int k = Math.max(0, i - 1); k < Math.min(m, i + 2); k++)
                    for (int l = Math.max(0, j - 1); l < Math.min(n, j + 2); l++)
                        lives += board[k][l] & 1;

                if ((board[i][j] == 1 && (lives == 2 || lives == 3)) || (board[i][j] == 0 && lives == 3))
                    board[i][j] |= (1 << 1); // Store at second least significant bit
            }
        }

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                board[i][j] >>= 1;
    }

    // Exploit every bit: save state of next gen in higher bit of each cell,
    // Since it could either be 0 or 1.
    public void gameOfLife2(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int live = getLiveNeighbor(board, i, j);
                if ((board[i][j] & 1) == 0)
                    board[i][j] |= (live == 3 ? 1 : 0) << 1;
                else
                    board[i][j] |= (live < 2 || live > 3 ? 0 : 1) << 1;
            }
        }
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] >>= 1;
    }

    // O(N^2) space
    public void gameOfLife_extraspace(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int[][] tmp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int live = getLiveNeighbor(board, i, j);
                if (board[i][j] == 0)
                    tmp[i][j] = (live == 3) ? 1 : 0;
                else
                    tmp[i][j] = (live < 2 || live > 3) ? 0 : 1;
            }
        }
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = tmp[i][j];
    }

    private int getLiveNeighbor(int[][] board, int row, int col) {
        int live = 0;
        for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, board.length); i++) { // Very nice trick!
            for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, board[i].length); j++) {
                if (i == row && j == col) continue;
                live += board[i][j] & 1; // Only difference
            }
        }
        return live;
    }

}
