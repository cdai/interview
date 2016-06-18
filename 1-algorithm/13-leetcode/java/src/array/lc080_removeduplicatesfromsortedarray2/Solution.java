package array.lc080_removeduplicatesfromsortedarray2;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * For example, Given sorted array nums = [1,1,1,2,2,3].
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 */
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        // Invariant: j points to position ready to insert
        //  property-1: [0,j) elements appear no more than twice
        //  property-2: nums[j] == nums[i] ?
        // Thus
        //  to maintain 2 => must move forward j if nums[i] is different (e.g. 1,1,1(j),1,1,2(i))
        //  to maintain 1 => must copy nums[i] to nums[j] before move j
        //  when loop terminates, [0,j) is what we want
        int j = 1;
        int c = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                if (c < 2) {
                    nums[j] = nums[i];
                    j++;
                }
                c++;
            } else {
                nums[j] = nums[i];
                j++;
                c = 1;
            }
        }
        return j;
    }
}
