package advanced.scan.sequence.lc300_longestincreasingsubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        List<Integer> seq = new ArrayList<>();
        int max = 0;
        for (int num : nums) {
            int ins = Collections.binarySearch(seq, num);
            if (ins < 0) {
                ins = -(ins + 1);
                if (ins == seq.size()) seq.add(num);// append new biggest
                else seq.set(ins, num);             // replace bigger one
            } /* else ignore exist num */
        }
        return seq.size();
    }

    // Very smart and general solution for this kinda problem
    public int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num); // exclusive
            if (i < 0) i = -(i + 1);
            dp[i] = num;            // replace with new incoming smaller num repeatly
            if (len == i) len++;    // but only increase len if more numbers
        }
        return len;
    }

    public int lengthOfLIS_nsquare(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            max = Math.max(max, dp[i]);
        }
        return max; // dp[i] is not global optimal
    }

    public int lengthOfLIS1(int[] nums) {
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

