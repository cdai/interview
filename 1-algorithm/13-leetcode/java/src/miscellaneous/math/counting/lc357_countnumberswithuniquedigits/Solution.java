package miscellaneous.math.counting.lc357_countnumberswithuniquedigits;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * Example: Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding [11,22,33,44,55,66,77,88,99])
 */
public class Solution {

    // eg.n=3   d1               d2                  d3
    //        [1~9]=9 * [0~9 exclude d1]=9 * [0~9 exclude d1/d2]=8
    public int countNumbersWithUniqueDigits(int n) {
        int total = 1; // 0
        for (int i = 1, cnt = 9; i <= n; i++) {
            total += cnt;
            cnt *= 10 - i; // reuse cnt
        }
        return total;
    }

    // My 2AC: backtracking solution. Note N -> N digits not N+1
    public int countNumbersWithUniqueDigits2(int n) {
        return doCount(n, new boolean[10], 0);
    }

    private int doCount(int n, boolean[] used, int d) {
        if (d == n) {       // error-1: d starts from 0~N-1
            return 1;
        }
        int total = 1;      // error-2: count internal nodes as well (root happens to be 0)
        for (int i = (d == 0) ? 1 : 0; i <= 9; i++) {
            if (!used[i]) {
                used[i] = true;
                total += doCount(n, used, d + 1);
                used[i] = false;
            }
        }
        return total;
    }

    // DP solution with counting technique
    // total(n) = total(n-1) + f(K) (9 * 9 * 8 * ... (9 - k + 2))
    public int countNumbersWithUniqueDigits22(int n) {
        if (n == 0) return 1;

        n = (n > 10) ? 10 : n; // Nice! Prevent overflow

        int total = 1;
        for (int i = 0, count = 1; i < n; i++) {
            count *= (i == 0) ? 9 : 10 - i;
            total += count;
        }
        return total;
    }

    // My 1AC: incremental thinking, but hard to read now...
    public int countNumbersWithUniqueDigits1(int n) {
        if (n <= 0) {
            return 1;
        }

        // Hint useful! Do it incrementally, not compute final cnt directly
        int cnt = 10;
        for (int i = 2; i <= n; i++) {
            // 12,13...98
            int nozero = 1;
            for (int j = 0; j < i; j++) {
                nozero *= (9 - j);
            }

            int haszero = i - 1;                // zero can appear in (910,901 or 90)
            for (int j = 0; j < i - 1; j++) {   // zero occupy one digit
                haszero *= (9 - j);
            }
            cnt += (nozero + haszero);
        }
        return cnt;
    }

}
