package buildingblock.searching.lc162_findpeakelement;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * Note: Your solution should be in logarithmic complexity.
 */
public class Solution {

    // O(logn)
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        return findPeakElement(nums, 0, nums.length - 1);
    }

    public int findPeakElement(int[] nums, int low, int high) {
        if (low >= high) {
            return -1;
        }

        // What invariant? FindPeakElement return peak index or -1 if not exist?
        int mid = (low + high) / 2;
        int peak1 = findPeakElement(nums, low, mid);
        int peak2 = findPeakElement(nums, mid + 1, high);
        if (peak1 == -1 && peak2 == -1) {
            // Two possible peak point in the middle
            return isPeak(nums, mid) ? mid : (isPeak(nums, mid + 1) ? mid + 1 : -1);
        }
        return (peak1 != -1) ? peak1 : peak2;
    }

    private boolean isPeak(int[] nums, int i) {
        return ((i == 0 || nums[i - 1] < nums[i]) &&
                (i == nums.length - 1 || nums[i] > nums[i + 1])) ? true : false;
    }

    // O(n) complexity
    public int findPeakElement3(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // invariant: nums[0] < ... < nums[i-1] < nums[i]
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

}
