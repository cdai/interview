package buildingblock.searching.lc240_searcha2dmatrix2;

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

    // O(M+N) time. (Like how many nodes you went through in BST)
    // I found this matrix is like a Min/Max Heap if you start from 0,0 or m,n and
    // the only thing you know is current number is greater or smaller than both left and right "child".
    // But you don't know how to make decision of where to go.
    // However, if you start from m,0 or 0,n, the matrix looks like a Binary Search Tree.
    // Then the path of search is just like to traverse the BST until we reach the target node.
    public boolean searchMatrix(int[][] A, int target) {
        if (A.length == 0 || A[0].length == 0) return false;
        for (int i = A.length - 1, j = 0; i >= 0 && j < A[0].length; ) {
            if (A[i][j] == target) return true;
            if (A[i][j] > target) i--;
            else j++;
        }
        return false;
    }

    // My 2AC: O(M+N) time
    // Integers in each row are sorted in ascending from left to right.
    // Integers in each column are sorted in ascending from top to bottom.
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) col--;
            else if (matrix[row][col] < target) row++;
            else if (matrix[row][col] == target) return true;
        }
        return false;
    }

    // My 1AC
    public boolean searchMatrix1(int[][] matrix, int target) {
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
