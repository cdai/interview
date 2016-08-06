package miscellaneous.bitmanipulation.lc231_poweroftwo;

/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public class Solution {

    // Power of two = 1, 2, 4, 8, 16...
    public boolean isPowerOfTwo(int n) {
        // Highly bit is 1 and must be only this 1-bit
        // 1(1), 2(10), 4(100), 8(1000), 16(10000)...
        return (n > 0) && ((n & (n - 1)) == 0);
    }

}
