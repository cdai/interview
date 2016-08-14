package buildingblock.hashing.lc349_intersectionoftwoarrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note: Each element in the result must be unique. The result can be in any order.
 */
public class Solution {

    // Why lambda is very slow...
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(e-> set.contains(e)).toArray();
    }

    // My 2nd: O(NlogN) time, O(1) space
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> intersect = new HashSet<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                intersect.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) {
            result[i++] = num;
        }
        return result;
    }

    // My 1st: it should have been very easy by HashSet API
    // But sadly Java mess it up, big gap between primitive array and collection...
    public int[] intersection1(int[] nums1, int[] nums2) {
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
