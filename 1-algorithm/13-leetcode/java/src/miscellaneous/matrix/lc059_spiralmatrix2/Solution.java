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
        if (n <= 0) return new int[0][0];
        int[][] ret = new int[n][n];
        int num = 1, left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) ret[top][i] = num++;
            top++;
            for (int i = top; i <= bottom; i++) ret[i][right] = num++;
            right--;
            for (int i = right; i >= left; i--) ret[bottom][i] = num++;
            bottom--;
            for (int i = bottom; i >= top; i--) ret[i][left] = num++;
            left++;
        }
        return ret;
    }

    public int[][] generateMatrix2(int n) {
        int[][] matrix = new int[n][n];
        if (n <= 0) return matrix;

        int num = 1, rowbegin = 0, rowend = n - 1, colbegin = 0, colend = n - 1;
        while (rowbegin <= rowend && colbegin <= colend) {
            for (int i = colbegin; i <= colend; i++) matrix[rowbegin][i] = num++;
            rowbegin++;

            for (int i = rowbegin; i <= rowend; i++) matrix[i][colend] = num++;
            colend--;

            //if (rowbegin > rowend || colbegin > colend) break; // not required for square matrix

            for (int i = colend; i >= colbegin; i--) matrix[rowend][i] = num++;
            rowend--;

            for (int i = rowend; i >= rowbegin; i--) matrix[i][colbegin] = num++;
            colbegin++;
        }
        return matrix;
    }

    // My 1AC
    public int[][] generateMatrix1(int n) {
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
