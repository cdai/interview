package advanced.dp.twodim.lc221_maximalsquare;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's
 * and return its area.
 * For example, given the following matrix:
 *  1 0 1 0 0
 *  1 0 1 1 1
 *  1 1 1 1 1
 *  1 0 0 1 0
 * Return 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(
                new Solution().maximalSquare(new char[][]{
                        "10100".toCharArray(),
                        "10111".toCharArray(),
                        "11111".toCharArray(),
                        "10010".toCharArray()
                }));
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) { // error2: error if extract m and n before this guard
            return 0;
        }

        // square[i][j] = max area length including i,j
        int[][] square = new int[matrix.length][matrix[0].length];
        int max = 0;

        // Optimize: avoid if in loop body
        for (int i = 0; i < matrix.length; i++) {
            square[i][0] = matrix[i][0] - '0';
            max = Math.max(max, square[i][0]);
        }
        for (int j = 0; j < matrix[0].length; j++) {
            square[0][j] = matrix[0][j] - '0';
            max = Math.max(max, square[0][j]);
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                int up = square[i - 1][j];
                int left = square[i][j - 1];
                int dia = square[i - 1][j - 1];
                int cur = matrix[i][j] - '0'; // error1: matrix is char, auto convert to int silently

                if (cur == 0) {
                    square[i][j] = 0;
                } else {
                    // Key!!! Max length at [i,j] is min of up,left,diagonal
                    square[i][j] = Math.min(Math.min(up, left), dia) + 1;
                    max = Math.max(max, square[i][j]);
                }
            }
        }
        return max * max;
    }

}
