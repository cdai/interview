package advanced.dp.statemac.lc276_paintfence;

/**
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 * Notice: n and k are non-negative integers.
 */
public class Solution {

    public int numWays2(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;   // don't forget to handle first 2 states specially
        int same = k, diff = k * (k - 1);
        while (n-- > 2) {       // we've already passed first two states
            int lastdiff = diff;
            diff = (k - 1) * diff + (k - 1) * same;
            same = lastdiff;
        }
        return same + diff;
    }

    // Sstart: start
    // Saccept: start, same, diff
    public int numWays_fulledition(int n, int k) {
        int start = k, same = 0, diff = 0;
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                diff = (k - 1) * start;
                same = start;
            } else {
                int lastdiff = diff;
                diff = (k - 1) * diff + (k - 1) * same;
                same = lastdiff;
            }
        }
        return n == 1 ? start : same + diff;
    }

}
