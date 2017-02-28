package buildingblock.table.hashing.lc001_twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an fundamentals.array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution.
 * The return format had been changed to zero-based indices.
 * Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class Solution {

    // My 3AC.
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> valIdx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valIdx.containsKey(target - nums[i])) {
                return new int[]{valIdx.get(target - nums[i]), i};
            }
            valIdx.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }

    // My 2nd: O(N) time and space
    // It works even duplicates exist like eg.[2,2,2,3] target=4
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> numPos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer j = numPos.get(target - nums[i]);
            if (j != null) {
                return new int[] { i, j };
            }
            numPos.put(nums[i], i);
        }
        return new int[] { -1, -1 };
    }

    // My 1st: the first pass is not necessary
    public int[] twoSum1(int[] nums, int target) {
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