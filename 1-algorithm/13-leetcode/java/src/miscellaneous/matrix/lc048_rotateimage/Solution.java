package miscellaneous.matrix.lc048_rotateimage;

import java.util.Arrays;

/**
 * You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise).
 * Follow up: Could you do this in-place?
 */
public class Solution {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        new Solution().rotate(matrix);
    }

    private static void print(int[][] matrix) {
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println();
    }

    // Amazing solution from crazy people!
    public void rotate(int[][] matrix) {
        reverseRow(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                swapByDiagonal(matrix, i, j);
            }
        }
    }

    private void reverseRow(int[][] matrix) {
        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = tmp;
        }
    }

    private void swapByDiagonal(int[][] matrix, int i, int j) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
    }

    // My 1AC: rotate one round by another
    public void rotate1(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        for (int row = 0; row < matrix.length / 2; row++) {
            for (int col = row; col < matrix.length - 1 - row; col++) { // error1: perform rotation before last
                doRotate(matrix,
                        col, row,
                        matrix.length - 1 - row, col,
                        matrix.length - 1 - col, matrix.length - 1 - row,
                        row, matrix.length - 1 - col);
            }
        }
    }

    private void doRotate(int[][] matrix,
                          int col1, int row1,
                          int col2, int row2,
                          int col3, int row3,
                          int col4, int row4) {   // error2: [x,y] in coordinate = [row,col] not [col,row]
        int tmp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row4][col4];
        matrix[row4][col4] = matrix[row3][col3];
        matrix[row3][col3] = matrix[row2][col2];
        matrix[row2][col2] = tmp;
    }

}
