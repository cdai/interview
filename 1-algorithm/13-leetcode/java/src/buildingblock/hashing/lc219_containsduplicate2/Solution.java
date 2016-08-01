package buildingblock.hashing.lc219_containsduplicate2;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k.
 */
public class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
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
