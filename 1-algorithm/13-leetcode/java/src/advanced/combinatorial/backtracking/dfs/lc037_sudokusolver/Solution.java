package advanced.combinatorial.backtracking.dfs.lc037_sudokusolver;

import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.'. You may assume that there will be only one unique solution.
 */
public class Solution {

    public static void main(String[] args) {
        new Solution().solveSudoku(new char[][]
                {"..9748...".toCharArray(),
                "7........".toCharArray(),
                ".2.1.9...".toCharArray(),
                "..7...24.".toCharArray(),
                ".64.1.59.".toCharArray(),
                ".98...3..".toCharArray(),
                "...8.3.2.".toCharArray(),
                "........6".toCharArray(),
                "...2759..".toCharArray()}
        );
    }

    // Very straightforward version inspired by leetcode discuss
    public void solveSudoku(char[][] board) {
        doSolve(board, 0, 0);
    }

    private boolean doSolve(char[][] board, int row, int col) {
        for (int i = row; i < 9; i++, col = 0) { // error: must reset col
            for (int j = col; j < 9; j++) {
                if (board[i][j] != '.') continue;
                for (char num = '1'; num <= '9'; num++) {
                    if (isValid(board, i, j, num)) {
                        board[i][j] = num;
                        if (doSolve(board, i, j + 1))
                            return true;
                        board[i][j] = '.';
                    }
                }
                return false;               // Note terminate here
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        int blkrow = (row / 3) * 3, blkcol = (col / 3) * 3; // Block no. is i/3, first element is i/3*3
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num || board[row][i] == num ||
                    board[blkrow + i / 3][blkcol + i % 3] == num)
                return false;
        return true;
    }


    // My 1AC
    public void solveSudoku1(char[][] board) {
        doSolve1(board, 0, 0);
    }

    private boolean doSolve1(char[][] board, int row, int col) {
        // Find next empty cell
        int[] empty = findNextEmptyCell(board, row, col);
        row = empty[0];
        col = empty[1];

        // Reach out of board, find solution!
        if (row == board.length) {
            return true;
        }

        // Try each candidate
        for (char c : candidates(board, row, col)) {
            board[row][col] = c;
            if (doSolve1(board, row, col + 1)) {
                return true;
            }
            board[row][col] = '.';
        }
        return false;
    }

    private int[] findNextEmptyCell(char[][] board, int row, int col) {
        for ( ; row < board.length; row++) {
            for ( ; col < board[row].length; col++) {
                if (board[row][col] == '.') {
                    return new int[]{ row, col };
                }
            }
            col = 0;
        }
        return new int[]{ row, col };
    }

    private char[] candidates(char[][] board, int row, int col) {
        boolean[] candidates = new boolean[10];
        Arrays.fill(candidates, true);
        int total = 9;

        // Scan row
        for (int i = 0; i < board[row].length; i++) {
            char c = board[row][i];
            if (c != '.') {
                if (candidates[c - '0']) {
                    total--;
                    candidates[c - '0'] = false;
                }
            }
        }

        // Scan column
        for (int i = 0; i < board.length; i++) {
            char c = board[i][col];
            if (c != '.') {
                if (candidates[c - '0']) {
                    total--;
                    candidates[c - '0'] = false;
                }
            }
        }

        // Scan square
        int sqrRow = (row / 3) * 3;
        int sqrCol = (col / 3) * 3;
        for (int i = sqrRow; i < sqrRow + 3; i++) {
            for (int j = sqrCol; j < sqrCol + 3; j++) {
                char c = board[i][j];
                if (c != '.') {
                    if (candidates[c - '0']) {
                        total--;
                        candidates[c - '0'] = false;
                    }
                }
            }
        }

        char[] result = new char[total];
        for (int i = 1, j = 0; i < candidates.length; i++) {
            if (candidates[i]) {
                result[j++] = (char) (i + '0');
            }
        }
        return result;
    }

}
