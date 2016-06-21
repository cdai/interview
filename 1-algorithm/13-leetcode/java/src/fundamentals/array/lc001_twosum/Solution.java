package fundamentals.array.lc001_twosum;

import java.util.HashMap;

/**
 * Given an fundamentals.array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * The return format had been changed to zero-based indices.
 * Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] error = new int[] {-1, -1};
        if (nums.length < 2) {
            return error;
        }

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            Integer j = map.get(target - nums[i]);
            if (j != null && i != j) { // error1: j!=null
                return new int[] {i, j};
            }
        }
        return error;
    }
}