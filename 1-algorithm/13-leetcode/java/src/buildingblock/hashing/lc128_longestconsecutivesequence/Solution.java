package buildingblock.hashing.lc128_longestconsecutivesequence;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{5, 7, 4, 6}));
    }

    public int longestConsecutive(int[] nums) {
        return 1;
    }

    // Correct, but TLE, doubt about its O(n)...
    public int longestConsecutive2(int[] nums) {
        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                continue;
            }

            // Expand range: "union" N-1 and N+1 as consective number at N
            int last = map.containsKey(n - 1) ? map.get(n - 1) : 0;
            int next = map.containsKey(n + 1) ? map.get(n + 1) : 0;
            int cur = last + 1 + next;
            map.put(n, cur);
            max = Math.max(max, cur);

            // Update all elements in range. Still O(n) totally?
            for (int i = n - 1; map.containsKey(i); i--) {
                map.put(i, cur);
            }
            for (int i = n + 1; map.containsKey(i); i++) {
                map.put(i, cur);
            }
        }
        return max;
    }

}
