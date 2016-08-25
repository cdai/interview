package buildingblock.table.lc128_longestconsecutivesequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{5, 7, 4, 6}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, false);
        }

        int max = 1;
        for (int n : nums) {
            if (map.get(n) == Boolean.TRUE) {
                continue;
            }

            // Update all elements in range. Still O(n) totally? Yes!
            // Since each element is updated only once ("continue" above)
            int length = 1;
            for (int i = n - 1; map.containsKey(i); i--, length++) {
                map.put(i, true);
            }
            for (int i = n + 1; map.containsKey(i); i++, length++) {
                map.put(i, true);
            }
            map.put(n, true);
            max = Math.max(max, length);
        }
        return max;
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

            // Update all elements in range. Still O(n) totally? No!
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

