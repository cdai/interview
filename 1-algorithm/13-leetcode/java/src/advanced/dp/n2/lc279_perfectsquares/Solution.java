package advanced.dp.n2.lc279_perfectsquares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numSquares(1339900));
    }

    private static List<Integer> leastNum = new ArrayList<>();
    static {
        leastNum.add(0);
    }

    // Classic dp using O(N) space.
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++)
            for (int j = 1; j * j <= i; j++)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        return dp[n];
    }

    // Stefan's "static" DP solution. Beat 95.88%
    public int numSquares_static(int n) {
        for (int m = leastNum.size(); m <= n; m++) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i * i <= m; i++) {
                min = Math.min(min, leastNum.get(m - i * i) + 1);
            }
            leastNum.add(min);
        }
        /* m > n (size = n + 1) */
        return leastNum.get(n);
    }

    // My 2AC: cache perfect square. O(Nsqrt(N)) time, O(N) space
    public int numSquares_dp(int n) {
        // 1.Get usable perfect square
        int[] sqr = new int[(int) Math.sqrt(n) + 1];
        for (int i = 1, j = 1; i < sqr.length; i++, j = i * i) {
            sqr[i] = j;
        }

        // 2.Init dp state
        int[] leastNum = new int[n + 1];
        Arrays.fill(leastNum, Integer.MAX_VALUE);
        for (int i = 1; i < sqr.length; i++) {
            leastNum[sqr[i]] = 1;
        }

        // 3.DP
        for (int i = 1; i <= n; i++) {
            if (leastNum[i] > 1) {
                for (int j = 1; j < sqr.length && sqr[j] < i; j++) {
                    leastNum[i] = Math.min(leastNum[i], leastNum[i - sqr[j]] + 1);
                }
            }
        }
        return leastNum[n];
    }

    // It's not applicable to use Greedy strategy. eg.12 -> 9+3*1 or 4*3
    public int numSquares_greedy(int n) {
        int[] sqr = new int[(int) Math.sqrt(n)];
        for (int i = 0, j = 1; i < sqr.length; i++, j = i * i) {
            sqr[i] = j;
        }

        int count = 0;
        for (int i = sqr.length - 1; i >= 0; i--) {
            count += n / sqr[i];
            n %= sqr[i];
        }
        return count;
    }

    // My 1AC: normal dp
    public int numSquares1(int n) {
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
