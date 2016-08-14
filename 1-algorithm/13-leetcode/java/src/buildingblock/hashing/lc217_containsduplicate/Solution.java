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

    // Save one operation by using return value of add
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (!numSet.add(num)) { // One operation: add() return false if element exists already
                return true;
            }
        }
        return false;
    }

    // My 2nd: O(N) time, O(N) space, TLE...
    // Note: Map is not necessary, since we exit if found a duplicate
    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (numSet.contains(num)) {
                return true;
            }
            numSet.add(num);
        }
        return false;
    }

    // TLE...
    public boolean containsDuplicate1(int[] nums) {
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

    public boolean containsDuplicate11(int[] nums) {
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
