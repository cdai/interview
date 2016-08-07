package advanced.greedy.lc376_wigglesubsequence;

/**
 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative.
 * A sequence with fewer than two elements is trivially a wiggle sequence.
 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative.
 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and
 * the second because its last difference is zero.
 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence.
 * A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence,
 * leaving the remaining elements in their original order.
 * Examples:
 * Input: [1,7,4,9,2,5]. Output: 6. The entire sequence is a wiggle sequence.
 * Input: [1,17,5,10,13,15,10,5,16,8]. Output: 7. There are several subsequences that achieve this length.
 *  One is [1,17,10,13,10,16,8].
 * Input: [1,2,3,4,5,6,7,8,9]. Output: 2.
 * Follow up:Can you do it in O(n) time?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().wiggleMaxLength(new int[]{3, 3, 3, 2, 5}));
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                maxLen++;
                while (i < n - 1 && nums[i] <= nums[i + 1]) { // deal with equals
                    i++;
                }
            } else if (nums[i - 1] > nums[i]) {
                maxLen++;
                while (i < n - 1 && nums[i] >= nums[i + 1]) { // deal with equals
                    i++;
                }
            } // nums[i - 1] == nums[i]
        }
        return maxLen;
    }

    public int wiggleMaxLength2(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        if (nums.length == 2) {
            return nums[0] == nums[1] ? 1 : 2; // error1: equals!
        }

        boolean isLastGreater = (nums[0] < nums[1]); // has problem to deal with equals...

        int maxLen = nums[0] == nums[1] ? 1 : 2;
        for (int i = 2; i < nums.length; i++) {
            if (isLastGreater) {
                if (nums[i - 1] > nums[i]) {
                    maxLen++;
                    isLastGreater = false;
                }
            } else {
                if (nums[i - 1] < nums[i]) {
                    maxLen++;
                    isLastGreater = true;
                }
            }
        }
        return maxLen;
    }

}
