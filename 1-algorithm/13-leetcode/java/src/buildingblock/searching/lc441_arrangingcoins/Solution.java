package buildingblock.searching.lc441_arrangingcoins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(496, arrangeCoins(123456));
    }

    public int arrangeCoins(int n) {
        long l = 0, r = n, t = 2 * (long) n; // avoid intermediate overflow
        while (l < r) {
            long m = (l + r + 1) / 2;
            if ((1 + m) * m <= t) l = m;
            else r = m - 1;
        }
        return (int) l;
    }

    public int arrangeCoins1(int n) {
        int l = 0, r = n;
        while (l < r) {
            int m = l + (r - l) / 2 + 1;
            //long t = (m % 2 == 0) ? (1 + m) * (m / 2) : (1 + m) / 2 * m; // avoid overflow and loss
            if (0.5 * m * m + 0.5 * m <= n) l = m;
            else r = m - 1;
        }
        return l;
    }

    public int arrangeCoins2(int n) {
        int cnt = 0;
        for (int i = 1; i <= n; n -= i++) {
            cnt++;
        }
        return cnt;
    }

}
