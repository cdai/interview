package buildingblock.sorting.heap.lc378_kthsmallestelementinasortedmatrix;

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

    // My 2nd: O(KlogK) time.
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
            return 0;
        }

        // 1.Build a min heap: tuple 0-row 1-column 2-value
        Queue<int[]> heap = new PriorityQueue<>((t1, t2) -> Integer.compare(t1[2], t2[2]));
        for (int i = 0; i < k && i < matrix[0].length; i++) {
            heap.offer(new int[] { 0, i, matrix[0][i] });
        }

        // 2.Poll k-1 times, then next one is the Kth smallest
        for (int i = 0; i < k - 1 && !heap.isEmpty(); i++) {
            int[] tuple = heap.poll();
            if (tuple[0] < matrix.length - 1) {
                heap.offer(new int[] { tuple[0] + 1, tuple[1], matrix[tuple[0] + 1][tuple[1]] });
            }
        }
        return heap.isEmpty() ? 0 : heap.poll()[2];
    }

    // My 1st: build heap with all elements. O(NlogK) time.
    public int kthSmallest1(int[][] matrix, int k) {
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
