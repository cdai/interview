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

    // 3AC
    public boolean searchMatrix(int[][] A, int target) {
        if (A.length == 0 || A[0].length == 0) return false;
        int m = A.length, n = A[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (A[mid / n][mid % n] == target) return true;
            if (A[mid / n][mid % n] < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }

    // My 2nd: O(logN)
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int num = matrix[mid / n][mid % n];     // key: index translation
            if (num > target) {
                high = mid - 1;
            } else if (num < target) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    // My 1st: didn't think of binary search...
    public boolean searchMatrix1(int[][] matrix, int target) {
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
