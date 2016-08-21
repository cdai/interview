package miscellaneous.bitmanipulation.lc201_bitwiseandofnumbersrange;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().rangeBitwiseAnd(2147483647, 2147483647));
    }

    // Brian Kernighan bits counting algorithm is applicable
    // Just remove lower 1's bit one by one to check if n = m
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n &= n - 1;
        }
        return n;
    }

    // The least significant bit must be different if m != n. Eg. 6=[0110],7=[0111]
    // That means it must be 0 if m & n.
    public int rangeBitwiseAnd2(int m, int n) {
        int i = 0;
        for ( ; m != n; i++) {
            m >>= 1;
            n >>= 1;
        }
        return m << i;
    }

    // My 1AC
    public int rangeBitwiseAnd1(int m, int n) {
        int result = 0, bit = 0;
        while (m >= 0 && n > 0) {
            // 1.Find if an even exists
            int i = m;
            for ( ; (i <= n) && (i < Integer.MAX_VALUE) && ((i & 1) == 1); i++);    // error1: m=n=2147483647 cause overflow!
                                                                                    // Note: INT_MAX is an odd, so it's safe to stop before it
            // 2.If so, that makes 0 bit
            if (i > n || i == Integer.MAX_VALUE) {
                result |= (1 << bit);
            }

            // 3.Narrow down range
            m >>= 1;
            n >>= 1;
            bit++;
        }
        return result;
    }

}
