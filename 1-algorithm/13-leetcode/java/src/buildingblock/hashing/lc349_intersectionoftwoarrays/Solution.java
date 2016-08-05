package buildingblock.hashing.lc349_intersectionoftwoarrays;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note: Each element in the result must be unique. The result can be in any order.
 */
public class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = toSet(nums1);
        Set<Integer> set2 = toSet(nums2);
        set1.retainAll(set2);
        return toArray(set1);
    }

    private Set<Integer> toSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        return set;
    }

    private int[] toArray(Set<Integer> set) {
        int[] nums = new int[set.size()];
        int i = 0;
        for (int n : set) {
            nums[i++] = n;
        }
        return nums;
    }

}
