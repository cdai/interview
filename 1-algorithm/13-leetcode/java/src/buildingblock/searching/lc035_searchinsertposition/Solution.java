package buildingblock.searching.lc035_searchinsertposition;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 *  [1,3,5,6], 5 → 2
 *  [1,3,5,6], 2 → 1
 *  [1,3,5,6], 7 → 4
 *  [1,3,5,6], 0 → 0
 */
public class Solution {

    // My 2nd: O(logN) with detailed assertion
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }

        // MustBe(0,n-1)
        int low = 0, high = nums.length - 1;

        // MustBe(low,high): (1) Initialization: invariant holds
        while (low <= high) {

            // MustBe(low,high) and low <= high
            int mid = low + (high - low) / 2;

            // MustBe(low,high) and low <= mid <= high
            if (nums[mid] < target) {

                // MustBe(low,high) and num[mid] < target <= num[high]
                // MustBe(mid+1,high)
                low = mid + 1;

                // MustBe(low,high): (2) Preservation: invariant holds
            } else if (nums[mid] > target) {

                // MustBe(low,high) and num[low] <= target < num[mid]
                // MustBe(low,mid-1)
                high = mid - 1;

                // MustBe(low,high)
            } else {
                return mid;
            }
        } // (3) Termination: range shrinks, so it must terminate

        // low > high, so it was low = high = mid (it's impossible to get low > high if low < high)
        // 1) target < num[mid], low=mid, high=mid-1, low is the insert position
        // 2) target > num[mid], high=mid, low=mid+1, low is the insert position too!
        return low;
    }

    // My 1st
    public int searchInsert1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;      //error: must be +1
            } else if (nums[mid] > target) {
                high = mid - 1;     //error: must be -1
            } else {
                return mid;
            }
        }
        return (target <= nums[low]) ? low : low + 1;
    }

}
