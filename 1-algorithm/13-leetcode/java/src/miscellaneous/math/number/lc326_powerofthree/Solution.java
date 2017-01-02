package miscellaneous.math.number.lc326_powerofthree;

/**
 * Given an integer, write a function to determine if it is a power of three.
 * Follow up: Could you do it without using any loop / recursion?
 */
public class Solution {

    // Normal idea
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) n /= 3;
        return n == 1;
    }

    public boolean isPowerOfThree_trick(int n) {
        return n > 0 && Math.pow(3, 19) % n == 0;
    }

    // Solution from leetcode discuss
    // For 32-bit, 1162261467 is 3^19,  3^20 is bigger than int
    // 3^19 % 3^N = 0. Eg. 3^19 % 27 = 3^16 * 3^3 % 3^3 = 0
    public boolean isPowerOfThree2(int n) {
        if (n <= 0) {
            return false;
        }

        int maxPowOf3 = 1;
        while (maxPowOf3 <= Integer.MAX_VALUE / 3) {
            maxPowOf3 *= 3;
        }
        return maxPowOf3 % n == 0;
    }

    // My 1AC
    public boolean isPowerOfThree1(int n) {
        if (n <= 0) {
            return false;
        }

        while (n > 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else {
                return false;
            }
        }
        return true;
    }

}
