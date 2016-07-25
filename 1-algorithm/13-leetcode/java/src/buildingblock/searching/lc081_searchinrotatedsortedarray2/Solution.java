package buildingblock.searching.lc081_searchinrotatedsortedarray2;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 */
public class Solution {

    public boolean search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private boolean search(int[] nums, int target, int low, int high) {
        /* MustBe(low,high) */
        if (low > high) {
            return false;
        }

        /* MustBe(low,high) */
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return true;
        }

        /* MustBe(low,high) and nums[mid] != target */
        if (nums[low] > nums[mid]) {
            // nums[low],...nums[0],...nums[mid],...nums[high]
            if (nums[mid] < target && target <= nums[high]) {
                return search(nums, target, mid + 1, high);
            }
            return search(nums, target, low, mid - 1);
        }

        if (nums[mid] > nums[high]) {
            // nums[low],...nums[mid],...nums[0],...nums[high]
            if (nums[low] <= target && target < nums[mid]) {
                return search(nums, target, low, mid - 1);
            }
            return search(nums, target, mid + 1, high);
        }
        return search(nums, target, low, mid - 1) || search(nums, target, mid + 1, high);
    }

}
