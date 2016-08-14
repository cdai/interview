package advanced.scan.window.lc219_containsduplicate2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k.
 */
public class Solution {

    // My 2nd: much easier and elegant using Sliding Window.
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return false;
        }

        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!numSet.add(nums[i])) {
                return true;
            }

            /* How many element in Set now? K */
            if (i >= k) {
                numSet.remove(nums[i - k]);
            }

            /* At most K-1 here */
        }
        return false;
    }

    // My 1st
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        Map<Integer,Integer> exist = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (exist.containsKey(nums[i])
                    && (i - exist.get(nums[i]) <= k)) {
                return true;
            }
            exist.put(nums[i], i);
        }
        return false;
    }

}
