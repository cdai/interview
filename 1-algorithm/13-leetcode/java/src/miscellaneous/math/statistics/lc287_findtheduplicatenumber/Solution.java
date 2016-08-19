package miscellaneous.math.statistics.lc287_findtheduplicatenumber;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 * Note:
 *  You must not modify the array (assume the array is read only).
 *  You must use only constant, O(1) extra space.
 *  Your runtime complexity should be less than O(n2).
 *  There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class Solution {

    // eg.[1,3,4,2,2]
    // mid = 2, count = 3 -> high = mid-1 = 1
    // mid = 1, count = 4 -> low = mid+1 = 2
    public int findDuplicate(int[] nums) {
        // Numbers in nums[N] are all in [1,N-1]
        int low = 1, high = nums.length - 1;

        // Note that low,mid,high have nothing with nums array
        // nums array only provides statistics info to locate that dup
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            // #num <= mid should =mid if no dup. eg.[1,2,3,4,5], mid=3
            // With dup=1, [1,1,2,3,4], count=3 > mid=2. means one smaller dup exists
            if (count > mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

}
