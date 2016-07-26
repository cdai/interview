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

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queens = new int[n];      // This describes where to place i-th queen, since they must not share the same row
        doSolve(result, queens, 0);     // It's much compressed than the whole chessboard!!!
        return result;
    }

    private void doSolve(List<List<String>> result, int[] queens, int k) {
        if (k == queens.length) {
            result.add(convert(queens));
            return;
        }

        // Try each position
        for (int i = 0; i < queens.length; i++) {
            if (isValid(queens, k, i)) {
                queens[k] = i;
                doSolve(result, queens, k + 1);
            }
        }
    }

    private List<String> convert(int[] queens) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < queens.length; i++) {
            char[] row = new char[queens.length];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            solution.add(new String(row));
        }
        return solution;
    }

    private boolean isValid(int[] queens, int k, int i) {
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
