package misc.math.lc007_reverseinteger;

/**
 * Reverse digits of an integer.
 *  Example1: x = 123, return 321
 *  Example2: x = -123, return -321
 */
public class Solution {

    public int reverse(int x) {
        int r = 0;
        while (x != 0) {
            if (r > Integer.MAX_VALUE / 10 || r < Integer.MIN_VALUE / 10) {  // error: may overflow after reversed
                return 0;
            }

            r = r * 10 + x % 10;
            x = x / 10;
        }
        return r;
    }

}
