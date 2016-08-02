package buildingblock.searching.lc378_kthsmallestelementinasortedmatrix;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix. Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * Example:
 *  matrix = [
 *      [ 1,  5,  9],
 *      [10, 11, 13],
 *      [12, 13, 15]
 *  ],
 * k = 8, return 13.
 * Note: You may assume k is always valid, 1 ≤ k ≤ n2.
 */
public class Solution {

    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        Queue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                heap.offer(matrix[i][j]);
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        return heap.isEmpty() ? 0 : heap.poll();
    }

    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int low = 0, high = matrix.length * matrix[0].length;
        while (low < high) {
            int mid = (low + high) / 2;

        }
        return 1;
    }

}
