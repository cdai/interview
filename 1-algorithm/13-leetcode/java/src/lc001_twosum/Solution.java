package lc001_twosum;

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] error = new int[] {-1, -1};
        if (nums.length < 2) {
            return error;
        }

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        Integer j;
        for (int i = 0; i < nums.length; i++) {
            j = map.get(target - nums[i]);
            if (j != null) {
                return new int[] {i + 1, j + 1};
            }
        }
        return error;
    }
}