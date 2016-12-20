package miscellaneous.matrix.lc311_sparsematrixmultiplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sparse matrices A and B, return the result of AB.
 */
public class Solution {

    public static void main(String[] args) {
        int[][] A = {{1, 0, 0}, {-1, 0, 3}};
        int[][] B = {{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};

        Solution sol = new Solution();
        print(sol.multiply_naive(A, B)); // [7,0,0],[-7,0,3]

        print(sol.multiply(A, B)); // [7,0,0],[-7,0,3]
    }

    // Naive approach
    public int[][] multiply_naive(int[][] A, int[][] B) {
        int m = A.length, l = A[0].length, n = B[0].length;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < l; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }


    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, l = A[0].length, n = B[0].length;
        int[][] C = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k < l; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < n; j++)
                    if (B[k][j] != 0)
                        C[i][j] += A[i][k] * B[k][j];
            }
        }
        return C;
    }

    /*
    public int[][] multiply_cmu(int[][] A, int[][] B) {
        int m = A.length, l = A[0].length, n = B[0].length;
        Map<Integer,Integer>[] rows = new Map[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                if (A[i][j] != 0) {
                    if (rows[i] == null) rows[i] = new HashMap<>();
                    rows[i].put(j, A[i][j]);
                }
            }
        }

        Map<Integer,Integer>[] cols = new Map[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < l; i++) {
                if (B[i][j] != 0) {
                    if (cols[i] == null) cols[i] = new HashMap<>();
                    cols[i].put(j, A[i][j]);
                }
            }
        }
    }
    */

    private static void print(int[][] A) {
        for (int[] row : A)
            System.out.println(Arrays.toString(row));
    }

}
