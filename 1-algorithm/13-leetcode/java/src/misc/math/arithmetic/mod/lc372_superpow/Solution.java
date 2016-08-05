package misc.math.arithmetic.mod.lc372_superpow;

import java.util.Arrays;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().superPow(2, new int[]{1, 2}));
    }

    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        if (isSafe(a, b)) {
            return a;
        }

        // mod property: (a*b)%p = ((a%p)*(b%p))%p
        int pow = superPow(a, half(b));
        if (isEven(b)) {
            return (pow * pow) % MOD;
        }
        return (((a * pow) % MOD) * pow) % MOD;
    }

    private boolean isSafe(int a, int[] b) {
        int i = 0;
        for (; i < b.length - 1 && b[i] == 0; i++) ;
        return i == b.length - 1 && b[i] == 1;
    }

    private boolean isEven(int[] b) {
        return b[b.length - 1] == 0;
    }

    private int[] half(int[] b) {
        int sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum = sum * 10 + b[i];
            b[i] = sum / 2;
            sum = sum % 2;
        }
        System.out.println(Arrays.toString(b));
        return b;
    }

    // Wrong and cumbersome...
    private int[] half2(int[] b) {
        int[] ret = new int[b.length];
        int sum = 0;
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            if (sum < 2) {
                sum = sum * 10 + b[i];
            } else {
                ret[j++] = sum / 2;
                sum = sum % 2;
            }
        }
        return (j == b.length) ? ret : Arrays.copyOf(ret, j);
    }

}
