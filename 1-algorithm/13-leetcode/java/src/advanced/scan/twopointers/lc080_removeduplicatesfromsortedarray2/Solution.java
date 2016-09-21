package advanced.scan.twopointers.lc080_removeduplicatesfromsortedarray2;

/**
 * Follow up for "Remove Duplicates": What if duplicates are allowed at most twice?
 * For example, Given sorted fundamentals.array nums = [1,1,1,2,2,3].
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 */
public class Solution {

    // My 3AC
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums)
            if (i < 2 || nums[i - 2] < num)
                nums[i++] = num;
        return i;
    }

    // Beautiful solution from stefan
    // Idea: keep num if it's different from num[i-k]
    public int removeDuplicates_general(int[] nums) {
        final int k = 2;
        int i = 0;
        for (int num : nums)
            if (i < k || nums[i - k] < num)
                nums[i++] = num;
        return i;
    }

    // My 2nd: use counter to count duplicate times. O(N) time
    // And Easy to extend to apply in general K case.
    public int removeDuplicates2(int[] nums) {
        // Invariant: elements in [0,j) appear at most K times
        final int k = 2;
        int j = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (++count <= k)     // error: ++count, since nums[i] should be counted now
                    nums[j++] = nums[i];
            } else {
                nums[j++] = nums[i];
                count = 1;
            }
        }
        return j;
    }

    // My 2nd: copy current and also next number if duplicate
    // but it is hard to extend for K times situation
    public int removeDuplicates3(int[] nums) {
        // Invariant: elements in [0,j) appear at most twice.
        int j = 0;
        for (int i = 0; i < nums.length; i++) {     // error: i and j must start from 0. eg.[1,1]
            if (i == 0 || nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
                if (i < nums.length - 1 && nums[i + 1] == nums[i]) {
                    nums[j++] = nums[++i];
                }
            }
        }
        return j;
    }

    // My 1st
    public int removeDuplicates1(int[] nums) {
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
