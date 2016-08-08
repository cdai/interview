package advanced.dp.n2.lc279_perfectsquares;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(1339900));
    }

    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] numsqr = new int[n + 1];
        Arrays.fill(numsqr, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            for (int j = 1, sqr = 1; sqr <= i; j++, sqr = j * j) { // error1: sqr=j*j must be here not in body, otherwise sqr<=j lose protect
                if (i == sqr) {
                    numsqr[i] = 1;
                } else {
                    numsqr[i] = Math.min(numsqr[i], numsqr[i - sqr] + 1);
                }
            }
        }
        return numsqr[n];
    }

}
