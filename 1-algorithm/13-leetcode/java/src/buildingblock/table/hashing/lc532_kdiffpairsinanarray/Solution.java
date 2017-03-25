package buildingblock.table.hashing.lc532_kdiffpairsinanarray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class Solution {

    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || k < 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int cnt = 0;
        for (int num : nums) {
            if (k == 0) {
                if (map.containsKey(num) && map.remove(num) >= 2) cnt++;
            } else {
                if (map.remove(num + k) != null) cnt++;
            }
        }
        return cnt;
    }

    // O(NlogN) time. Sort in ascending order and search num-k
    public int findPairs1(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer,Boolean> map = new HashMap<>();
        int cnt = 0;
        for (int num : nums) {
            if (map.getOrDefault(num - k, false)) {
                cnt++;
                map.put(num - k, false);
            }
            if (!map.containsKey(num)) {
                map.put(num, true);
            }
        }
        return cnt;
    }

}
