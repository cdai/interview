package miscellaneous.bitmanipulation.lc191_numberof1bits;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has
 * (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 */
public class Solution {

    public static void main(String[] args) {
        // Java shift operator test
        // MIN_VALUE:   10000000000000000000000000000000
        // >> 1:        11000000000000000000000000000000
        // >>>1:        01000000000000000000000000000000
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 1));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >>> 1));

        // -1:          11111111111111111111111111111111
        //  1:          00000000000000000000000000000001
        // -1 & 1:      00000000000000000000000000000001
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-3 & 1));
        System.out.println(Integer.toBinaryString(-1 - 1));

        System.out.println(new Solution().hammingWeight(11));
        System.out.println(new Solution().hammingWeight(Integer.MIN_VALUE));
        System.out.println(new Solution().hammingWeight((int) 2147483648L));
    }

    // -1:          11111111111111111111111111111111
    //  1:          00000000000000000000000000000001
    // -1 & 1:      00000000000000000000000000000001
    // -1 - 1:      11111111111111111111111111111110
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n-1;
            count++;
        }
        return count;
    }

    // My 2AC
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    // you need to treat n as an unsigned value
    public int hammingWeight1(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if (n == 0) {
                break;
            }
            if ((n & 1) == 1) {     // error1: & is lower than ==, so must use parentheses
                cnt++;
            }
            n >>>= 1;               // both >> and >>> are ok, since we only shift right 31 times
        }                           // but it affects if we can terminate early
        return cnt;
    }

}
