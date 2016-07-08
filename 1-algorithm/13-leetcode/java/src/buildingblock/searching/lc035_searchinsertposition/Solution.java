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

    public int searchInsert(int[] nums, int target) {
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
