package buildingblock.searching.lc081_searchinrotatedsortedarray2;

/**
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 */
public class Solution {

    // My 3AC: O(N) worst
    public boolean search(int[] A, int t) {
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (A[m] == t) return true;
            if (A[l] < A[m]) {          // left half is sorted
                if (A[l] <= t && t < A[m]) r = m - 1;
                else l = m + 1;         // right half: [7,1,4,5,6] or all the same eg.[4,3,4,4,4]
            } else if (A[l] > A[m]) {   // right half MUST be sorted or same. Note: DON'T use A[m] < A[r]
                if (A[m] < t && t <= A[r]) l = m + 1;
                else r = m - 1;
            } else l++;                 // A[l] == A[m], so it's safe to shrink
        }
        return false;
    }

    // Solution from leetcode discuss: a little simpler than mine in if-condition
    // The key is to consider from one bound: mid and high OR mid and low
    public boolean search21(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < nums[high]) {       // Second half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] > nums[high]) {// First half may be sorted eg.[0,1,4,5,0] or all the same eg.[4,4,4,5,0]
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {                            // A[mid] = A[high] and A[mid]<>target, so it's safe to shrink from high bound
                high--;
            }
        }
        return false;
    }

    // My 2nd: O(logN) but O(N) in the worst case
    public boolean search2(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] < nums[high]) {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if (nums[low] < nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {                            // case: A[low] => A[mid] => A[high]
                if (nums[mid] == nums[high]) {  // it must have: A[low]=A[mid] or A[mid]=A[high], otherwise two pivots exist
                    high--;                     // (pivot-1 is between A[low] > A[mid], pivot-2 is between A[mid] > A[high])
                } else {
                    low++;
                }
            }
        }
        return false;
    }

    // My 1st: recursion is not a must
    public boolean search1(int[] nums, int target) {
        return search1(nums, target, 0, nums.length - 1);
    }

    private boolean search1(int[] nums, int target, int low, int high) {
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
                return search1(nums, target, mid + 1, high);
            }
            return search1(nums, target, low, mid - 1);
        }

        if (nums[mid] > nums[high]) {
            // nums[low],...nums[mid],...nums[0],...nums[high]
            if (nums[low] <= target && target < nums[mid]) {
                return search1(nums, target, low, mid - 1);
            }
            return search1(nums, target, mid + 1, high);
        }
        return search1(nums, target, low, mid - 1) || search1(nums, target, mid + 1, high);
    }

}
