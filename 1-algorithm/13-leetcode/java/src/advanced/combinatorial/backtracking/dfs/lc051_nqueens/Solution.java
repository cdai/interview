package advanced.combinatorial.backtracking.dfs.lc051_nqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * For example, there exist two distinct solutions to the 4-queens puzzle:
 *  [
 *      [".Q..",  // Solution 1
 *      "...Q",
 *      "Q...",
 *      "..Q."],
 *      ["..Q.",  // Solution 2
 *      "Q...",
 *      "...Q",
 *      ".Q.."]
 *  ]
 */
public class Solution {

    // My 2AC: understand queen[i] means put queen of ith row to queen[i] column
    // [".Q..",   queen[0]=1
    //  "...Q",   queen[1]=3
    //  "Q...",   queen[2]=0
    //  "..Q."],  queen[0]=1
    // O(N!*N) time and O(N) space
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        doSolveNQueens(result, new int[n], 0);
        return result;
    }

    private void doSolveNQueens(List<List<String>> result, int[] queens, int row) {
        if (row == queens.length) {
            result.add(convert(queens));
            return;
        }
        for (int col = 0; col < queens.length; col++) { // Start from 0 each time, so isValid needa check column conflict
            if (isValid(queens, col, row)) {
                queens[row] = col;
                doSolveNQueens(result, queens, row + 1);
            }
        }
    }

    private List<String> convert(int[] queens) {
        List<String> solution = new ArrayList<>();
        for (int q : queens) {
            char[] row = new char[queens.length];
            Arrays.fill(row, '.');
            row[q] = 'Q';
            solution.add(new String(row));
        }
        return solution;
    }

    // Check position of queen from [0,row-1], see if we can set queens[row] = col
    private boolean isValid(int[] queens, int col1, int row1) {
        for (int row2 = 0; row2 < row1; row2++) {             // Only check [0,row-1], so no need to init queen[] to -1 or reset it
            if (queens[row2] == col1 || row1 - row2 == Math.abs(col1 - queens[row2])) {
                return false;
            }
        }
        return true;
    }


    // My 1AC
    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];      // This describes where to place i-th queen, since they must not share the same row
        doSolve1(result, queens, 0);     // It's much compressed than the whole chessboard!!!
        return result;
    }

    private void doSolve1(List<List<String>> result, int[] queens, int k) {
        if (k == queens.length) {
            result.add(convert(queens));
            return;
        }

        // Try each position
        for (int i = 0; i < queens.length; i++) {
            if (isValid1(queens, k, i)) {
                queens[k] = i;
                doSolve1(result, queens, k + 1);
            }
        }
    }

    private List<String> convert1(int[] queens) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            char[] row = new char[queens.length];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            solution.add(new String(row));
        }
        return solution;
    }

    private boolean isValid1(int[] queens, int k, int i) {
        // We want to set queen of k to position i,
        // So check all [0,k-1] queens to see if any conflict
        for (int j = 0; j < k; j++) {
            if (queens[j] == i ||               // column conflict
                    (queens[j] - (k - j) == i) ||   // diagonal line conflict
                    (queens[j] + (k - j) == i)) {   // diagonal line conflict
                return false;
            }
        }
        return true;
    }

}
