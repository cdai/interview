package advanced.dp.n2.lc300_longestincreasingsubsequence;

import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example, given [10, 9, 2, 5, 3, 7, 101, 18]. The longest increasing subsequence is [2, 3, 7, 101],
 *  therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length. Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(new Solution().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // maxlen including nums[i], otherwise hard to transfer state
        int[] maxlen = new int[nums.length];
        Arrays.fill(maxlen, 1);     // error1: must init to 1 all

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxlen[i] = Math.max(maxlen[i], maxlen[j] + 1);
                }
            }
        }

        int max = 0;
        for (int len : maxlen) { // error2: this's not globally optimal, so the last one is NOT the most optimal
            max = Math.max(max, len);
        }
        return max;
    }

}
