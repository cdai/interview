package fundamentals.array.lc026_removeduplicatesfromsortedarray;

/**
 * Given a sorted fundamentals.array, remove the duplicates in place such that each element appear only once
 * and return the new length. Do not allocate extra space for another fundamentals.array,
 * you must do this in place with constant memory.
 *
 * For example, Given input fundamentals.array nums = [1,1,2], Your function should return length = 2,
 * with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // Invariant: [0, j] must be unique (inclusive & j >= 0)
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }
}
