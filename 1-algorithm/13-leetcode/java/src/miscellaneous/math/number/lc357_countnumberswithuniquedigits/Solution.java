package miscellaneous.math.number.lc357_countnumberswithuniquedigits;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 * Example: Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding [11,22,33,44,55,66,77,88,99])
 */
public class Solution {

    public int countNumbersWithUniqueDigits(int n) {
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
