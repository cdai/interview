package misc.bitmanipulation.lc191_numberof1bits;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has
 * (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().hammingWeight(11));
        System.out.println(new Solution().hammingWeight(Integer.MIN_VALUE));
        System.out.println(new Solution().hammingWeight((int) 2147483648L));
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
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
