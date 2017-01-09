package buildingblock.searching.lc154_findminimuminrotatedsortedarray2;

/**
 * Follow up for "Find Minimum in Rotated Sorted Array": What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 */
public class Solution {

    // n[m] == n[r] -> r=m wrong! eg.[1,1,-1,1]
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > nums[r]) l = m + 1;
            else if (nums[m] < nums[r]) r = m;
            else r--;
        }
        return nums[l];
    }

    // Inspired from leetcode discuss: O(N) in the worst case
    // But this approach will not return the correct index, only value
    public int findMin3(int[] nums) {
        // Min element must be [0, n-1]
        int low = 0, high = nums.length - 1;

        // MustBe(low,high)
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {           // [mid,high] is disordered
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {    // [mid,high] is in order
                high = mid;
            } else {                                // nums[mid]=nums[high]
                high--;                             // key: why it's safe to shrink from high bound...
            }                                       // because nums[high] is impossible to be the only min (otherwise, nums[mid]>nums[high])
        }
        return nums[low];
    }

    // My 2nd: O(NlogN) in the worst case
    public int findMin2(int[] nums) {
        return doFindMin(nums, 0, nums.length - 1);
    }

    private int doFindMin(int[] nums, int low, int high) {
        if (low >= high) {
            return nums[low];
        }

        int mid = low + (high - low) / 2;
        if (nums[mid] > nums[high]) {
            return doFindMin(nums, mid + 1, high);
        }
        if (nums[mid] < nums[low]) {
            return doFindMin(nums, low, mid);
        }
        //return Math.min(doFindMin(nums, low, mid), doFindMin(nums, mid + 1, high));
        return doFindMin(nums, low, high - 1);
    }

    // My 1st
    // Where problems arise:
    //  [2,3,4,4,4] => min=4, since nums[2](4) < nums[4](4) is false, continue to search in [2,4]
    //  [4,4,4,1,4] => min=4, since nums[2](4) <= nums[4](4) is true, continue to search in [0,2]
    // Then how can we know if left or right half is rotated or not?
    // So you know nothing if nums[low] = nums[mid] = nums[high], both half is required to search.
    public int findMin1(int[] nums) {
        return findMin1(nums, 0, nums.length - 1);
    }

    private int findMin1(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        int mid = (low + high) / 2;

        // Right half is ordered, continue search on [low,mid]
        if (nums[mid] < nums[high]) {
            return findMin1(nums, low, mid);
        }

        // Right half is disordered, continue search on [mid + 1,high]
        if (nums[mid] > nums[high]) {
            return findMin1(nums, mid + 1, high);
        }

        // Uncertain, search both sides. eg.[4,2,4,4,4] or [4,4,4,2,4]
        return Math.min(findMin1(nums, low, mid), findMin1(nums, mid + 1, high));
    }

}
