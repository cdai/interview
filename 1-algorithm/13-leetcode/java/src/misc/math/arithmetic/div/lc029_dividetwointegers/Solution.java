package misc.math.arithmetic.div.lc029_dividetwointegers;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 */
public class Solution {

    public int divide(int dividend, int divisor) {
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
