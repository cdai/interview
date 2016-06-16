package lc050_pow;

/**
 * Implement pow(x, n).
 */
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == -1) {
            return 1/x;
        } else if (n == 1) {
            return x;
        }

        double r = myPow(x, n / 2);
        r = r * r;

        if (n % 2 != 0) {
            return (n < 0) ? r * 1/x : r * x;
        }
        return r;
    }
}
