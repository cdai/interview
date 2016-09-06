package buildingblock.searching.lc033_searchinrotatedsortedarray;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class Solution {

    // Inspired from leetcode discuss
    public int search2(int[] nums, int target) {
        int n = nums.length;

        // 1.Find pivot (smallest element position)
        // This is actually another problem 153
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        // 2.Pretend to binary search on a sorted array
        //  But transform index when we need to compare (realmid)
        int pivot = low;
        low = 0; high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; // how to avoid overflow...
            int realmid = (mid + pivot) % n;
            if (nums[realmid] == target) {
                return realmid;
            } else if (nums[realmid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // My 2nd: O(logN)
    public int search(int[] nums, int target) {
        // target must be [0,n-1] or doesn't exist in array
        int low = 0, high = nums.length - 1;

        // MustBe(low,high)
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < nums[high]) {
                if (nums[mid] < target && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            } else {
                if (nums[low] <= target && target < nums[mid]) high = mid - 1;
                else low = mid + 1;
            }
        }
        // low is the insert position, but -1 is required
        return -1;
    }

    // My 1st
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {    // [low,mid] is sorted: must be <=, otherwise [3,1],1 => -1 not 1
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {    // [mid,high] is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return (nums[low] == target) ? low : -1;
    }

}
