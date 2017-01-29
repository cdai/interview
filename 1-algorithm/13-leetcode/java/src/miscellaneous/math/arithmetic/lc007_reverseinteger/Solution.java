package miscellaneous.math.arithmetic.lc007_reverseinteger;

/**
 * Reverse digits of an integer.
 *  Example1: x = 123, return 321
 *  Example2: x = -123, return -321
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Math.abs(Integer.MIN_VALUE));
    }

    public int reverse(int n) {
        int rev = 0;
        for (; n != 0; n /= 10) {
            int d = n % 10;
            int tmp = rev * 10 + d;
            if ((tmp - d) / 10 != rev) return 0; // must overflow if not equal!
            rev = tmp;
        }
        return rev;
    }

    // 3AC.
    public int reverse3(int n) {
        int rev = 0, sign = (n >= 0) ? 1 : -1;
        while (n != 0) {
            int d = Math.abs(n % 10);
            if (rev > Integer.MAX_VALUE / 10 ||
                    (rev == Integer.MAX_VALUE / 10 && d > 7)) {
                return 0;
            }
            rev = rev * 10 + d;
            n /= 10;
        }
        return sign * rev;
    }

    // we don't need to use Math.abs or special logic to handle '+' and '-', because the sign can be kept during calculating
    public int reverse2(int x) {
        int ret = 0;
        while (x != 0) {    // error: != not > if deal with both pos and neg here
            if (ret > Integer.MAX_VALUE / 10 || ret < Integer.MIN_VALUE / 10) { // For 2147483647 it's impossible we got "214748364" and a digit > 7 like 8463847412 will cause Runtime error at the very first
                return 0;
            }
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return ret;
    }

    // My AC: confused on overflow handle...
    public int reverse12(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0;
        }

        // Now it's safe to remove sign
        boolean isNeg = false;
        if (x < 0) {
            x = -x;
            isNeg = true;
        }

        int ret = 0;
        while (x > 0) {
            if (ret > Integer.MAX_VALUE / 10
                /*|| (ret == Integer.MAX_VALUE / 10 && isNeg ? x % 10 > 8 : x % 10 > 7)*/) {
                return 0;
            }
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return isNeg ? -ret : ret;
    }

    // My 1AC: This is good!
    public int reverse1(int x) {
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
