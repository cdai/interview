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

    // Case 1: A[m-1] < A[m] > A[m+1] => return (too messy tough), so go left and keep A[m]
    // Case 2: A[m-1] > A[m] > A[m+1] => go left
    // Case 3: A[m-1] < A[m] < A[m+1] => go right
    // Case 4: A[m-1] > A[m] < A[m+1] => both ok, then go left to simplify if-else
    // So don't bother to return from case 1 directly, then we have one case to go right
    // Note don't worry missing peak, we only need one
    public int findPeakElement(int[] A) {
        int n = A.length, l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (A[m] < A[m + 1]) l = m + 1; /* l<r<n => m+1<=r<n */
            else r = m;
        }
        return l;
    }

    // O(logN) solution from leetcode discuss
    public int findPeakElement21(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = mid1 + 1;

            // "The key mindset is to climb the rising slope."
            // It doesn't matter if you missed a peak
            if (nums[mid1] < nums[mid2]) {
                low = mid2;
            } else{
                high = mid1;
            }
        }
        return low;
    }

    // My 2nd: sequential search with O(N) time
    public int findPeakElement2(int[] nums) {
        int i = 0;
        for (; i < nums.length - 1 && nums[i] < nums[i + 1]; i++);

        // Stop at i=len-1 or A[i]>=A[i+1]. In both cases, peak is i
        return i;
    }

    // My 1st: O(logn)
    public int findPeakElement1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        return findPeakElement1(nums, 0, nums.length - 1);
    }

    public int findPeakElement1(int[] nums, int low, int high) {
        if (low >= high) {
            return -1;
        }

        // What invariant? FindPeakElement return peak index or -1 if not exist?
        int mid = (low + high) / 2;
        int peak1 = findPeakElement1(nums, low, mid);
        int peak2 = findPeakElement1(nums, mid + 1, high);
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
