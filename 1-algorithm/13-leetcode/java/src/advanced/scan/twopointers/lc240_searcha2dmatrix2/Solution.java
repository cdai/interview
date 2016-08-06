package advanced.scan.twopointers.lc240_searcha2dmatrix2;

import java.util.Arrays;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example, consider the following matrix:
 * [
 *  [1,   4,  7, 11, 15],
 *  [2,   5,  8, 12, 19],
 *  [3,   6,  9, 16, 22],
 *  [10, 13, 14, 17, 24],
 *  [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().searchMatrix(new int[][]{{-1, 3}}, 1));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][m - 1] < target) {
                continue;
            }

            if (Arrays.binarySearch(matrix[i], target) != -1) {
                return true;
            }
        }
        return false;
    }

}
