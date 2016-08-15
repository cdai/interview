package advanced.scan.twopointers.lc283_movezeroes;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
 */
public class Solution {

    // My 2nd: O(N)
    public void moveZeroes(int[] nums) {
        // Invariant: [0,j) has no zero
        int j = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[j++] = num;
            }
        }
        Arrays.fill(nums, j, nums.length, 0); // arg "to" is exclusive
    }

    // My 1st
    public void moveZeroes1(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        for ( ; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

}
