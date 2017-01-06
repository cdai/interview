package lintcode.binarysearch;

/**
 */
public class Solution {

    // LintCode-14: First Position of Target
    // For a given sorted array (ascending order) and a target number, find the first index of this number in O(log n) time complexity.
    // If the target number does not exist in the array, return -1.
    // Does duplicate have affect on binary search?
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) { /* invariant: t in [l,r]. stop if l=r to avoid dead loop */
            int m = l + (r - l) / 2;
            if (nums[m] == target) r = m; // doesn't make progress if l=r
            else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return nums[l] == target ? l : -1; /* l=r */
    }

}
