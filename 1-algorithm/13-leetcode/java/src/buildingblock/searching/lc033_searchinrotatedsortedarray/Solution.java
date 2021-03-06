package buildingblock.searching.lc033_searchinrotatedsortedarray;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). You are given a target value to search.
 * If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 */
public class Solution {

    // Note A[l] <= A[m]!! Edge case: [3,1],1.
    // l=m=r-1: A[l]=A[m]=3<>target, then inner if not work, go l=m+1 which is correct!
    // l=m=r: A[l]=A[m]=A[r]=target=1
    public int search(int[] A, int target) {
        int l = 0, r = A.length - 1;
        while (l <= r) { /* invariant: target in [l,r] */
            int m = l + (r - l) / 2;
            if (A[m] == target) return m;
            if (A[l] <= A[m]) {
                if (A[l] <= target && target < A[m]) r = m - 1;
                else l = m + 1;
            } else { /* must at least one half is not rotated */
                if (A[m] < target && target <= A[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }

    public int search31(int[] A, int target) {
        // Find pivot (smallest)
        int n = A.length, l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (A[m] > A[r]) l = m + 1;
            else r = m;
        }
        // Compare target with "real" mid number by pivot
        int pv = l;
        for (l = 0, r = n - 1; l <= r;) {
            int m = l + (r - l) / 2, rm = (m + pv) % n;
            if (A[rm] == target) return rm;
            if (A[rm] < target) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

    // Inspired from leetcode discuss
    public int search21(int[] nums, int target) {
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
    public int search2(int[] nums, int target) {
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
