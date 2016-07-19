package misc.matrix.lc054_spiralmatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * For example, Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int row = matrix.length, col = matrix[0].length;
        int total = row * col, i = 0, j = 0;
        while (total > 0) {    // error2: it's hard to determine when to stop, so we need counter total
            int right = col - 1, down = row - 1;
            int left = (right > 0 && down > 0) ? (col - 1) : 0;
            int up = (left > 0) ? (row - 1) : 0;
            boolean isStop = (up == 0);

            while (right-- > 0) {
                result.add(matrix[i][j++]);
                total--;
            }
            while (down-- > 0) {
                result.add(matrix[i++][j]);
                total--;
            }
            while (left-- > 0) {
                result.add(matrix[i][j--]);
                total--;
            }
            while (up-- > 0) {
                result.add(matrix[i--][j]);
                total--;
            }

            // There is no another spiral
            if (isStop) {
                result.add(matrix[i][j]); // error1: one remaining if terminate early, eg.otherwise: [[1,2,3,4,5]]->[1,2,3,4]
                break;
            }
            // Move to starting point of next spiral
            row -= 2;
            col -= 2;
            i++;
            j++;
        }
        return result;
    }

}
