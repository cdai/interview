package lintcode.math;

/**
 */
public class Solution {

    // 3-Digit Counts Medium
    // Edge case: k=0, cnt init to 1. And for k=0,n=0 return 1 not 0.
    public int digitCounts(int k, int n) {
        if (n < 0) return 0;
        long cnt = (k == 0) ? 1 : 0;
        for (long i = 1, q = n; i <= n; i *= 10, q /= 10) {
            long pre = n / (i * 10), cur = q % 10, suf = n % i;
            cnt += pre * i;
            if (k != 0) { // 0 cannot be leading digit
                cnt += (k < cur ? i : (k == cur ? suf + 1 : 0));
            }
        }
        return (int) cnt;
    }

}
