package buildingblock.table.hashing.lc036_validsudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 */
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] row = new boolean[9][9], col = new boolean[9][9], sub = new boolean[9][9];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!Character.isDigit(board[i][j])) continue;
                int k = i / 3 * 3 + j / 3, num = board[i][j] - '1'; // from 0~9
                if (row[i][num] || col[j][num] || sub[k][num]) {
                    return false;
                }
                row[i][num] = col[j][num] = sub[k][num] = true;
            }
        }
        return true;
    }

    public boolean isValidSudoku3(char[][] board) {
        if (board.length == 0 || board[0].length == 0) return true;
        int m = board.length, n = board[0].length;

        // Validate row
        for (int i = 0; i < m; i++) {
            boolean[] exist = new boolean[10];
            for (int j = 0; j < n; j++) {
                if (!Character.isDigit(board[i][j])) continue;
                int k = board[i][j] - '0';
                if (exist[k]) return false;
                else exist[k] = true;
            }
        }

        // Validate col
        for (int j = 0; j < n; j++) {
            boolean[] exist = new boolean[10];
            for (int i = 0; i < m; i++) {
                if (!Character.isDigit(board[i][j])) continue;
                int k = board[i][j] - '0';
                if (exist[k]) return false;
                else exist[k] = true;
            }
        }

        // Validate square
        for (int i = 0; i < m; i += 3) {
            for (int j = 0; j < n; j += 3) {
                boolean[] exist = new boolean[10];
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (!Character.isDigit(board[k][l])) continue;
                        int q = board[k][l] - '0';
                        if (exist[q]) return false;
                        else exist[q] = true;
                    }
                }
            }
        }
        return true;
    }

    // O(N^2) and String append cost, but more readable solution from Stefan.
    public boolean isValidSudoku2(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; i++) {     // must be 9*9 board
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.' &&
                    (!seen.add(num + " in row " + i) ||
                    !seen.add(num + " in col " + j) ||
                    !seen.add(num + " in block " + (i / 3) + "-" + (j / 3)))) {
                    return false;
                }
            }
        }
        return true;
    }

    // My 1AC: naive approach with O(N^2) time and O(1) space
    public boolean isValidSudoku1(char[][] board) {
        boolean[] used = new boolean[10];

        // Validate row
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isUsed(board[i][j], used)) {
                    return false;
                }
            }
            reset(used);
        }

        // Validate line
        for (int j = 0; j < board.length; j++) { // assume square
            for (int i = 0; i < board.length; i++) {
                if (isUsed(board[i][j], used)) {
                    return false;
                }
            }
            reset(used);
        }

        // Validate square
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3) {
                if (!isSquareValid(board, i, j, used)) {
                    return false;
                }
                reset(used);
            }
        }
        return true;
    }

    private boolean isSquareValid(char[][] board, int i, int j, boolean[] used) {
        for (int m = i; m < i + 3; m++) {   // error1: it's unwise trying to avoid m,n using i,j alone
            for (int n = j; n < j + 3; n++) {
                if (isUsed(board[m][n], used)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isUsed(char c, boolean[] used) {
        if (c == '.') {
            return false;
        }

        int k = c - '0';
        if (used[k]) {
            return true;
        }
        used[k] = true;
        return false;
    }

    private void reset(boolean[] used) {
        for (int i = 1; i < used.length; i++) {
            used[i] = false;
        }
    }

}
