package advanced.dyprog.onedim.lc070_climbingstairs;

import java.util.HashMap;
import java.util.Map;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class Solution {

    private Map<Integer,Integer> cache = new HashMap<>();

    public int climbStairs(int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        int n1;
        if (cache.containsKey(n-1)) {
            n1 = cache.get(n-1);
        } else {
            n1 = climbStairs(n-1);
            cache.put(n-1, n1);
        }

        int n2;
        if (cache.containsKey(n-2)) {
            n2 = cache.get(n-2);
        } else {
            n2 = climbStairs(n-2);
            cache.put(n-2, n2);
        }

        return n1 + n2;
    }

    // Dynamic programming
    public int climbStairs2(int n) {
        int n0 = 1;
        int n1 = 1;
        int n2 = 2;
        for (int i = 2; i <= n; i++) {
            n2 = n0 + n1;
            n0 = n1;
            n1 = n2;
        }
        return (n < 2) ? n1 : n2;
    }

}
