package buildingblock.table.hashing.lc454_4sum2;

import java.util.HashMap;
import java.util.Map;

/**
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.
 */
public class Solution {

    // O(N^2) time and space. Use hashtable like what we did in 2Sum.
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B.length; j++)
                sum.put(A[i] + B[j], sum.getOrDefault(A[i] + B[j], 0) + 1);

        int cnt = 0;
        for (int i = 0; i < C.length; i++)
            for (int j = 0; j < D.length; j++)
                cnt += sum.getOrDefault(-C[i] + -D[j], 0);
        return cnt;
    }

}
