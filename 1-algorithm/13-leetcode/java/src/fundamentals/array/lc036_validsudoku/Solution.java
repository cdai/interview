package fundamentals.array.lc036_validsudoku;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note: A valid Sudoku board (partially filled) is not necessarily solvable.
 * Only the filled cells need to be validated.
 */
public class Solution {

    public boolean isValidSudoku(char[][] board) {
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
