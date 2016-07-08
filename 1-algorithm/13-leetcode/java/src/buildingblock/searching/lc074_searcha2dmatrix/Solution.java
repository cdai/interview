package buildingblock.searching.lc074_searcha2dmatrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties: Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example, Consider the following matrix:
 *  [
 *      [1,   3,  5,  7],
 *      [10, 11, 16, 20],
 *      [23, 30, 34, 50]
 *  ]
 *  Given target = 3, return true.
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // Decide row
        int low = 0, high = matrix.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else if (matrix[mid][0] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }

        if (matrix[low][0] == target) {
            return true;
        }
        int row = (matrix[low][0] > target) ? low - 1 : low;
        if (row == -1) {
            return false;
        }

        // Decide position
        low = 0;
        high = matrix[row].length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (matrix[row][mid] > target) {
                high = mid - 1;
            } else if (matrix[row][mid] < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return (matrix[row][low] == target);
    }

}
