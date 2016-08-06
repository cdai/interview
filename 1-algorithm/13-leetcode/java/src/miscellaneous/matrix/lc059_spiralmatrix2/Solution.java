package miscellaneous.matrix.lc059_spiralmatrix2;

/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * For example, Given n = 3, You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class Solution {

    public int[][] generateMatrix(int n) {
        if (n == 0) {
            return new int[0][0];
        }

        int[][] matrix = new int[n][n];
        int total = n * n, val = 1, i = 0, j = 0;
        while (total > 0) {
            int right = n - 1, down = n - 1;
            int left = (right > 0 && down > 0) ? (n - 1) : 0;
            int up = (left > 0) ? (n - 1) : 0;
            boolean isStop = (up == 0);

            while (right-- > 0) {
                matrix[i][j++] = val++;
                total--;
            }
            while (down-- > 0) {
                matrix[i++][j] = val++;
                total--;
            }
            while (left-- > 0) {
                matrix[i][j--] = val++;
                total--;
            }
            while (up-- > 0) {
                matrix[i--][j] = val++;
                total--;
            }

            // There is no another spiral
            if (isStop) {
                matrix[i][j] = val++;
                break;
            }
            // Move to starting point of next spiral
            n -= 2;
            i++;
            j++;
        }
        return matrix;
    }

}
