package miscellaneous.math.arithmetic.lc029_dividetwointegers;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
public class Solution {

    // Inspired from leetcode discuss
    // Very handful using Long to handle overflow
    public int divide(int dividend, int divisor) {
        boolean isNeg = (dividend < 0) ^ (divisor < 0); // Nice trick! Logical XOR!
        long did = Math.abs((long) dividend);
        long div = Math.abs((long) divisor);

        long count = 0;
        while (did >= div) {
            for (long i = div, j = 1; i <= did; i <<= 1, j <<= 1) { // Faster than i+=i, j+=j
                did -= i;
                count += j;
            }
        }

        count = isNeg ? -count : count;                 // Multiplication is not allowed, otherwise use sign as int for convenience
        count = Math.min(count, Integer.MAX_VALUE);     // Nice! Be aware of overflow. MAX_MIN / -1.
        count = Math.max(count, Integer.MIN_VALUE);
        return (int) count;
    }

    // Doesn't work for negative
    public int divide_pos(int dividend, int divisor) {
        if (dividend < divisor) {
            return 0;
        }

        long count = 0;
        for (long i = divisor, j = 1; i <= dividend; i += i, j += j) {
            dividend -= i;
            count += j;
        }
        return (int) count + divide(dividend, divisor);
    }

    // My 1AC: correct thinking but too messy not using Long
    public int divide1(int dividend, int divisor) {
        if (divisor == 0) { // avoid dead loop
            return 0;
        }
        if (divisor == Integer.MIN_VALUE) { // avoid Math.abs(divisor) overflow
            return (dividend == Integer.MIN_VALUE) ? 1 : 0;
        }

        int d1 = (dividend == Integer.MIN_VALUE) ? Math.abs(dividend + 1) : Math.abs(dividend); // error: -2147483648 / -1 = 2147483647
        int d2 = Math.abs(divisor);
        int r = 0;
        while (d1 >= d2) {
            int multi = 1;
            while (d1 >= d2) {
                r = r + multi;
                d1 = d1 - d2;

                if (d2 > Integer.MAX_VALUE - d2) {
                    break;
                }

                multi = multi + multi;
                d2 = d2 + d2;
            }
            d2 = Math.abs(divisor);
        }

        if (dividend == Integer.MIN_VALUE && (d1 + 1 == divisor)) { // dividend == Integer.MIN_VALUE
            r++;
        }
        return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? r : -r;
    }

}
