package lintcode.string;

/**
 */
public class Solution {

    // 397-Longest Increasing Continuous Subsequence
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A.length == 0) return 0;
        int maxinc = 1, maxdec = 1;
        for (int i = 1, inc = 1, dec = 1; i < A.length; i++) {
            if (A[i - 1] == A[i]) inc = dec = 1;
            else if (A[i - 1] < A[i]) {
                inc++;
                dec = 1;
            } else {
                dec++;
                inc = 1;
            }
            maxinc = Math.max(maxinc, inc);
            maxdec = Math.max(maxdec, dec);
        }
        return Math.max(maxinc, maxdec);
    }

}
