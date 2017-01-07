package miscellaneous.math.arithmetic.lc050_pow;

import java.util.Stack;

/**
 * Implement pow(x, n).
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().myPow(2, -5));
    }

    // 4AC
    public double myPow(double x, int n) {
        double ret = 1, pow = x;
        for (long i = Math.abs((long) n); i > 0; i >>= 1) {
            if (i % 2 == 1) ret *= pow;
            pow *= pow;
        }
        return n > 0 ? ret : 1 / ret;
    }

    public double myPow4_stack(double x, int n) {
        Stack<Integer> s = new Stack<>();
        for (int i = n; i != 0; i /= 2) s.push(i);

        double pow = 1;
        while (!s.isEmpty()) {
            pow *= pow;
            if (s.pop() % 2 != 0) pow *= (n > 0) ? x : 1 / x;
        }
        return pow;
    }

    // My 3AC. O(logN) time.
    public double myPow3(double x, int n) {
        double ret = 1, pow = x;            // n != 0 doesn't work due to sign
        for (long absn = Math.abs((long) n); absn > 0; absn >>= 1) {
            if ((absn & 1) == 1) ret *= pow;// avoid sign affect. don't use %
            pow *= pow;
        }
        return n > 0 ? ret : 1 / ret;
    }

    // Very nice iterative solution
    // b      : 7 -> 3 -> 1
    // pow(a^): 1    2    4
    // ret(a^): 1    3    7
    public double myPow2(double x, int n) {
        double ret = 1, pow = x;
        long absn = Math.abs((long) n);
        while (absn > 0) {      // n != 0 doesn't work due to sign
            if ((absn & 1) == 1)// avoid sign affect. don't use %
                ret *= pow;     // pow not x. eg.7->3->1. ret=x,pow=x^2. ret=x^3,pow=x^4.
            pow *= pow;
            absn >>= 1;
        }
        return n > 0 ? ret : 1 / ret;
    }

    public double myPow_iterative(double x, int n) {
        double ret = 1;
        for (long absn = Math.abs((long) n), i; absn > 0; absn -= i) {
            double pow = x;
            for (i = 1; i < absn / 2; i <<= 1)
                pow *= pow;
            ret *= pow;
        }
        return n > 0 ? ret : 1 / ret; // Would be any different for 1/ret here or 1/x each step?
    }

    // Overflow risk if we Math.abs(n). O(logN) time.
    public double myPow_recursive(double x, int n) {
        if (n == 0) return 1;

        double pow = myPow(x, n / 2);
        if (n % 2 == 0)
            return pow * pow;
        return (n < 0) ? pow * pow / x : pow * pow * x;
    }

    // My 1AC
    public double myPow1(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == -1) {
            return 1/x;
        } else if (n == 1) {
            return x;
        }

        double r = myPow1(x, n / 2);
        r = r * r;

        if (n % 2 != 0) {
            return (n < 0) ? r * 1/x : r * x;
        }
        return r;
    }
}
