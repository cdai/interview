package buildingblock.searching.lc154_findminimuminrotatedsortedarray2;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 */
public class Solution {

    // Where problems arise:
    //  [2,3,4,4,4] => min=4, since nums[2](4) < nums[4](4) is false, continue to search in [2,4]
    //  [4,4,4,1,4] => min=4, since nums[2](4) <= nums[4](4) is true, continue to search in [0,2]
    // Then how can we know if left or right half is rotated or not?
    // So you know nothing if nums[low] = nums[mid] = nums[high], both half is required to search.
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        int mid = (low + high) / 2;

        // Right half is ordered, continue search on [low,mid]
        if (nums[mid] < nums[high]) {
            return findMin(nums, low, mid);
        }

        // Right half is disordered, continue search on [mid + 1,high]
        if (nums[mid] > nums[high]) {
            return findMin(nums, mid + 1, high);
        }

        // Uncertain, search both sides. eg.[4,2,4,4,4] or [4,4,4,2,4]
        return Math.min(findMin(nums, low, mid), findMin(nums, mid + 1, high));
    }

}
