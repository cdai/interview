package miscellaneous.math.counting.lc400_nthdigit;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 */
public class Solution {

    public int findNthDigit(int n) {
        // 1.Find range [1-9], [10,100], [100,1000]...
        int len = 1, base = 1;
        for (long cnt = 9; n > len * cnt; cnt *= 10) {
            n -= len * cnt;
            len++;
            base *= 10;
        }
        // 2.Find target number
        String s = String.valueOf(base + (n - 1) / len); // n starts from 1 not 0
        // 3.Find nth digit
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }

}
