package advanced.combinatorial.backtracking.dfs.lc051_nqueens;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution2 {

    public static void main(String[] args) {
        System.out.println(new Solution2().solveNQueens(9));
    }

    // Correct, but TLE when n >= 9
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        doSolve(result, board, 0, 0, n);
        return result;
    }

    private void doSolve(List<List<String>> result, char[][] board, int i, int j, int k) {
        if (k == 0) {
            result.add(convert(board));
            return;
        }

        for ( ; i < board.length; i++) {
            for ( ; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    mark(board, i, j);
                    doSolve(result, board, i, j, k - 1);
                    clear(board, i, j);
                }
            }
            j = 0;   // error: must reset manually if reuse i, j
        }
    }

    private void mark(char[][] board, int i, int j) {
        // Mark ith row
        for (int m = 0; m < board[i].length; m++) {
            ++board[i][m];
        }
        // Mark ith column
        for (int m = 0; m < board.length; m++) {
            ++board[m][j];
        }
        // Mark diagonal line
        for (int m = i, n = j; (0 <= m) && (0 <= n); m--, n--) {
            ++board[m][n];
        }
        for (int m = i, n = j; (0 <= m) && (n < board[m].length); m--, n++) {
            ++board[m][n];
        }
        for (int m = i, n = j; (m < board.length) && (0 <= n); m++, n--) {
            ++board[m][n];
        }
        for (int m = i, n = j; (m < board.length) && (n < board[m].length); m++, n++) {
            ++board[m][n];
        }
        // Mark queen position
        board[i][j] = 'Q';
    }

    private void clear(char[][] board, int i, int j) {
        // Mark ith row
        for (int m = 0; m < board[i].length; m++) {
            --board[i][m];
        }
        // Mark ith column
        for (int m = 0; m < board.length; m++) {
            --board[m][j];
        }
        // Mark diagonal line
        for (int m = i, n = j; (0 <= m) && (0 <= n); m--, n--) {
            --board[m][n];
        }
        for (int m = i, n = j; (0 <= m) && (n < board[m].length); m--, n++) {
            --board[m][n];
        }
        for (int m = i, n = j; (m < board.length) && (0 <= n); m++, n--) {
            --board[m][n];
        }
        for (int m = i, n = j; (m < board.length) && (n < board[m].length); m++, n++) {
            --board[m][n];
        }
        // Mark queen position
        board[i][j] = 0;
    }

    private List<String> convert(char[][] board) {
        List<String> result = new ArrayList<>(board.length * board.length);
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                row.append((board[i][j] != 'Q') ? "." : "Q" );
            }
            result.add(row.toString());
        }
        return result;
    }

}
