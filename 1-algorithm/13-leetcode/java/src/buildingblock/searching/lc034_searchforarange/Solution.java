package buildingblock.searching.lc034_searchforarange;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 */
public class Solution {

    // My 2nd: two binary searches with O(logN) time
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[]{ -1, -1 };
        if (nums.length == 0) {
            return range;
        }

        // 1.Find first occurence of target
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                range[0] = mid;
                high = mid - 1;     // find target, but continue binary search on [low,mid)
            }
        }

        if (range[0] == -1) {
            return range;
        }

        // 2.Find last occurence of target
        low = 0; high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                range[1] = mid;
                low = mid + 1;      // find target, but continue binary search on (mid,high]
            }
        }
        return range;
    }

    // My 1st: divide and conquer
    public int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{ -1, -1 };
        }

        int[] range = doSearch(nums, target, 0, nums.length - 1);
        if (range.length == 0) {
            return new int[]{ -1, -1 };
        }
        return range;
    }

    private int[] doSearch(int[] nums, int target, int low, int high) {
        // Base
        if (low >= high) {
            return (nums[low] == target) ? new int[] { low, low } : new int[0];
        }

        // Divide: log(n)
        int mid = (low + high) / 2;
        int[] range1 = doSearch(nums, target, low, mid);        // error1: range1 should be left
        int[] range2 = doSearch(nums, target, mid + 1, high);   // error2: [low,mid-1],[mid+1,high] is wrong, this is not binary search!

        // Merge: constant
        if (range1.length > 1 && range2.length > 1) {
            return new int[] { range1[0], range2[1] };
        } else {
            return (range1.length > 1) ? range1 : range2;
        }
    }

}
