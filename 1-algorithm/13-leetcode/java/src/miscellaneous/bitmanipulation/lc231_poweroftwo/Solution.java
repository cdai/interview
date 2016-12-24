package miscellaneous.bitmanipulation.lc231_poweroftwo;

/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public class Solution {

    // My 3AC
    // Math.pow(n,a) = a^n, speaking "2 power n"
    // Power of two = 2^n: 2^1, 2^2, 2^3...
    // My 2AC: still don't remember trick: n&(n-1)==0
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        // Only one 1-bit
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count == 1;
    }

    // Power of two = 1, 2, 4, 8, 16...
    public boolean isPowerOfTwo1(int n) {
        // Highly bit is 1 and must be only this 1-bit
        // 1(1), 2(10), 4(100), 8(1000), 16(10000)...
        return (n > 0) && ((n & (n - 1)) == 0);
    }

}
