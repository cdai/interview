package misc.matrix.lc073_setmatrixzeroes;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up: Did you use extra space? A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class Solution {

    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        // Mark if there is 0
        boolean colHasZero = false;
        for (int[] row : matrix) {
            if (row[0] == 0) {
                colHasZero = true;
                break;
            }
        }
        boolean rowHasZero = false;
        for (int col : matrix[0]) {
            if (col == 0) {
                rowHasZero = true;
                break;
            }
        }

        // Mark original 0 using first row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Set to 0 for middle area
        for (int i = 1; i < matrix.length; i++) {   // leave [0,0] alone, it only affect first row and col which is handle later
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Set to 0 for first row and col if needed
        if (rowHasZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (colHasZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }

}