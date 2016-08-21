package miscellaneous.bitmanipulation.lc371_sumoftwointegers;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example: Given a = 1 and b = 2, return 3.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getSum(10, 1));
    }

    // Result: 0+0=0,  0+1=1,  1+0=1,  1+1=0  This is Xor!
    // Carry:  0+0->0, 0+1->0, 1+0->0, 1+1->1 This is And!
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1); // move carry to higher bit
    }

    // Wrong! Only works for positive
    // No carry: 0+0=0, 0+1=1, 1+0=1, 1+1=0. This is Xor!
    // With carry: 0+0+1=1, 0+1+1=0, 1+0+1=0, 1+1+1=1.
    public int getSum_naive(int a, int b) {
        int sum = 0;
        for (int i = 0, carry = 0; a != 0 || b != 0 || carry > 0; i++, a >>= 1, b >>= 1) {
            sum |= (((a & 1) ^ (b & 1) ^ carry) << i);
            carry = carry == 0 ? (a & 1) & (b & 1) : (a & 1) | (b & 1);
        }
        return sum;
    }

    public int getSum1(int a, int b) {
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
