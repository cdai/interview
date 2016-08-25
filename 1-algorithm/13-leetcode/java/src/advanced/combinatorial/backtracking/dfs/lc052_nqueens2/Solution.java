package advanced.combinatorial.backtracking.dfs.lc052_nqueens2;

/**
 * Follow up for N-Queens problem. Now, instead outputting board configurations,
 * return the total number of distinct solutions.
 */
public class Solution {

    // My 2AC: O(N!*N) time and O(N) space
    public int totalNQueens(int n) {
        return doTotalNQueens(new int[n], 0);
    }

    private int doTotalNQueens(int[] queens, int row) {
        if (row == queens.length) {
            return 1;
        }
        int total = 0;
        for (int col = 0; col < queens.length; col++) {
            if (isValid(queens, col, row)) {
                queens[row] = col;
                total += doTotalNQueens(queens, row + 1);
            }
        }
        return total;
    }

    private boolean isValid(int[] queens, int col1, int row1) {
        for (int row2 = 0; row2 < row1; row2++) {
            if (queens[row2] == col1 || row1 - row2 == Math.abs(col1 - queens[row2])) {
                return false;
            }
        }
        return true;
    }


    // My 1AC
    public int totalNQueens1(int n) {
        return doSolve(new int[n], 0);
    }

    private int doSolve(int[] queens, int k) {
        if (k == queens.length) {
            return 1;
        }

        // Try each position
        int total = 0;
        for (int i = 0; i < queens.length; i++) {
            if (isValid1(queens, k, i)) {
                queens[k] = i;
                total += doSolve(queens, k + 1);
            }
        }
        return total;
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
