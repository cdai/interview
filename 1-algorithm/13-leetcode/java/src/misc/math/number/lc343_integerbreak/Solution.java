package misc.math.number.lc343_integerbreak;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
 * Return the maximum product you can get.
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().integerBreak(4));
    }

    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        int result = 1;
        while (n > 4) {
            n -= 3;
            result *= 3;
        }
        return result * n;
    }

    // O(n^2) DP
    public int integerBreak2(int n) {
        if (n < 2) {
            return 1;
        }

        // max(prod[j],j): eg.prod[3]=2
        int[] prod = new int[n + 1];
        prod[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) { // j is linked to i not n
                prod[i] = Math.max(prod[i], Math.max(prod[j], j) * Math.max(prod[i - j], i - j));
            }
        }
        return prod[n];
    }

}
