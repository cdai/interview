package miscellaneous.bitmanipulation.lc371_sumoftwointegers;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example: Given a = 1 and b = 2, return 3.
 */
public class Solution {

    public int getSum(int a, int b) {
        while (b != 0) {
            int c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a;
    }

    public int getSum2(int a, int b) {
        int result = 0;
        int carry = 0;
        while (a > 0 || b > 0) {
            carry = (a & ~1) & (b & ~1);
            //result = (result << 1) &
        }
        return result;
    }

}
