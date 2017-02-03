package buildingblock.sorting.merge.lc349_intersectionoftwoarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Given two arrays, write a function to compute their intersection.
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Note: Each element in the result must be unique. The result can be in any order.
 */
public class Solution {

    // 3AC. Binary search O(NlogN) time O(1) space solution.
    public int[] intersection(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        List<Integer> inter = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (i > 0 && A[i - 1] == A[i]) continue;
            if (Arrays.binarySearch(B, A[i]) >= 0) inter.add(A[i]);
        }

        int[] ret = new int[inter.size()];
        for (int i = 0; i < inter.size(); i++) {
            ret[i] = inter.get(i);
        }
        return ret;
    }

    // Why lambda is very slow...
    public int[] intersection_lambda(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(set::contains).toArray();
    }

    public int[] intersection3(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        List<Integer> inter = new ArrayList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length; ) {
            if (i > 0 && A[i - 1] == A[i]) i++;     // Bypass duplicate in num1
            else if (j > 0 && B[j - 1] == B[j]) j++;
            else if (A[i] < B[j]) i++;
            else if (A[i] > B[j]) j++;
            else inter.add(A[i++]);                 // It's ok move only i since we bypass duplicate
        }

        int[] ret = new int[inter.size()];
        for (int i = 0; i < inter.size(); i++) {
            ret[i] = inter.get(i);
        }
        return ret;
    }

    // My 2nd: O(NlogN) time, O(1) space
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> intersect = new HashSet<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] > nums2[j]) j++;
            else if (nums1[i] < nums2[j]) i++;
            else {
                intersect.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (int num : intersect) result[i++] = num;
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

    public int[] intersection_set(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        Set<Integer> inter = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) inter.add(num);
        }

        int[] ret = new int[inter.size()];
        int i = 0;
        for (int num : inter) {
            ret[i++] = num;
        }
        return ret;
    }

}
