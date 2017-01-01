package fundamentals.array.lc280_wigglesort;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that: nums[0] <= nums[1] >= nums[2] <= nums[3]....
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {3, 5, 2, 1, 6, 4};
        sol.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    // O(N) solution: swap prev and cur if odd idx and prev>cur or even idx and prev<cur. Why?
    // Case-1:  A[i-2]>=A[i-1]>A[i] -> A[i-2] >= A[i] < A[i-1]
    // (i=odd)  or A[i-2]>=A[i-1]<=A[i-1]
    // Case-2:  A[i-2]<=A[i-1]<=A[i] -> A[i-2] <= A[i] >= A[i-1]
    // (i=even) or A[i-2]<=A[i-1]>A[i]
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int prev = nums[i - 1];
            if ((i % 2 == 1) == (prev > nums[i])) {
                nums[i - 1] = nums[i];
                nums[i] = prev;
            }
        }
    }

}
