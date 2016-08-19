package miscellaneous.math.number.lc343_integerbreak;

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

    // My 2AC: more natural DP solution
    public int integerBreak(int n) {
        // 1.Init except dp[n], since it cannot be itself and must break into two positive
        int[] maxProd = new int[n + 1];
        for (int i = 1; i < n; i++) {
            maxProd[i] = i;
        }

        // 2.Compute max product
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) { // Note: only check j <= i - j
                maxProd[i] = Math.max(maxProd[i], maxProd[j] * maxProd[i - j]);
            }
        }
        return maxProd[n];
    }

    // Stefan concise explanation:
    // "If an optimal product contains a factor f >= 4,
    // then you can replace it with factors 2 and f-2 without losing optimality,
    // as 2*(f-2) = 2f-4 >= f. So you never need a factor greater than or equal to 4,
    // meaning you only need factors 1, 2 and 3 (and 1 is of course wasteful and
    // you'd only use it for n=2 and n=3, where it's needed).
    // For the rest I agree, 3*3 is simply better than 2*2*2, so you'd never use 2 more than twice."

    // My 1AC
    public int integerBreak1(int n) {
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
