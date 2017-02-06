package advanced.scan.cumulative.lc363_maxsumofrectanglenolargerthank;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 */
public class Solution {

    // O(M*N^2) time, optimize for M is larger (more rows than cols)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int[] sum = new int[m + 1];
            for (int h = i; h < n; h++) {
                for (int j = 1; j <= m; j++) {
                    sum[j] += matrix[j - 1][h];
                }
                TreeSet<Integer> presums = new TreeSet<>();
                for (int j = 0, presum = 0; j <= m; j++) {
                    presum += sum[j];
                    Integer closest = presums.ceiling(presum - k);
                    if (closest != null) {
                        max = Math.max(max, presum - closest);
                    }
                    presums.add(presum);
                }
            }
        }
        return max;
    }

    // O(N*M^2) time, optimize for N is larger (more cols than rows)
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n + 1];
            for (int h = i; h < m; h++) {
                for (int j = 1; j <= n; j++) {
                    sum[j] += matrix[h][j - 1];
                }
                TreeSet<Integer> presums = new TreeSet<>();
                for (int j = 0, presum = 0; j <= n; j++) {
                    presum += sum[j];
                    Integer closest = presums.ceiling(presum - k);
                    if (closest != null) {
                        max = Math.max(max, presum - closest);
                    }
                    presums.add(presum);
                }
            }
        }
        return max;
    }

}
