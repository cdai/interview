package buildingblock.hashing.lc217_containsduplicate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
public class Solution {

    // TLE...
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> exist = new HashSet<>();
        for (int n : nums) {
            if (exist.contains(n)) {
                return true;
            }
            exist.add(n);
        }
        return false;
    }

}
