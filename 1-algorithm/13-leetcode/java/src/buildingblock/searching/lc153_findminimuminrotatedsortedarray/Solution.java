package buildingblock.searching.lc153_findminimuminrotatedsortedarray;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2). Find the minimum element.
 * You may assume no duplicate exists in the array.
 */
public class Solution {

    // My 3AC: O(logN) time
    public int findMin(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (A[m] > A[r]) l = m + 1;
            else r = m; /* A[m] <= A[r], m could be min */
        }
        return A[l];
    }

    // Totally wrong, A[l] <= A[m] says nothing...
    public int findMin_wrong(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (A[l] > A[m]) r = m;
            else l = m + 1;
        }
        return A[l];
    }

    // My 2nd: without bunch of asserts, just open eyes on key points
    // 1) Initialization: invariant definition and init value
    // 2) Preservation: make invariant hold
    // 3) Termination: range shrinks all the way so it must terminate, then return -1, index or value
    public int findMin2(int[] nums) {
        int low = 0, high = nums.length - 1;

        // Min element MustBe(low,high)
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[high]) { // last half is disordered
                low = mid + 1;
            } else { // last half is ordered or doesn't exist (mid=high)
                high = mid;
            }
        }

        // MustBe(low,high) and low=high
        return nums[low];
    }

    // My 1st
    // Test case:
    // 1.Odd or even number of values
    // 2.Min is first, last or first half or second half
    // 3.Empty, only-one array
    // 4.positive, negative, zero
    public int findMin1(int[] nums) {
        /* MustBe(0,N-1): min value must be in range [0,N-1] */
        int low = 0, high = nums.length - 1;

        /* MustBe(low,high): (1) when initialization, invariant holds */
        while (low < high) {

            /* MustBe(low,high) and low < high */
            int mid = (low + high) / 2;

            /* Since at least mid = (low + low + 1) / 2 = low */
            /* => MustBe(low,high) and low <= mid < high */
            if (nums[mid] < nums[high]) {

                /* Since nums[mid]<...<mid[high]: Pivot is not in (mid,high] (but could be nums[mid])*/
                /* => MustBe(low,mid) */
                high = mid;
            } else {

                /* Since nums[low]<...nums[mid]<...nums[0]<...<nums[high] */
                /* MustBe(mid + 1,high) */
                low = mid + 1;
            }

            /* MustBe(low,high): (2) when preservation, invariant holds */
        }

        /* Since invariant holds when intialization, preservation and termination */
        /* => MustBe(low,high) and low == high (if low > high, MustBe(low,high) assertion would fail) */
        return nums[low];
    }

    /* Very casual version without solid thought of correctness */
    public int findMin12(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < nums[high]) { // right half is correct, so min is in left half
                high = (mid == high) ? mid - 1 : mid;
            } else {
                low = (mid == low) ? mid + 1 : mid;
            }
        }
        return nums[low]; // outofbounds if nums is null or empty
    }

}
