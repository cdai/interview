package advanced.greedy.lc045_jumpgame2;

import java.util.Arrays;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example: Given array A = [2,3,1,1,4]. The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *  Note: You can assume that you can always reach the last index.
 */
public class Solution {

    // TLE...
    public int jump_naive(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, nums.length + 1);    // Trick: for Math.min and steps[i]+1 not overflow
        steps[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length && j <= i + nums[i]; j++) {
                steps[j] = Math.min(steps[j], steps[i] + 1);
            }
        }
        return steps[nums.length - 1];
    }

    public int jump_setifreach(int[] nums) {
        int[] steps = new int[nums.length];
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = reach + 1; j < nums.length && j <= i + nums[i]; j++) {
                steps[j] = steps[i] + 1;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return steps[nums.length - 1];
    }

    // Nice single loop solution! Just check how far you can go in current reachable range.
    // Eg.      [2, 3, 1, 1, 4]
    // reach     2     4
    // farthest  2  4
    // step      1     2
    public int jump(int[] nums) {
        int step = 0, reach = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) { // reach last so stop before 1, eg.[1,1]

            // Record how far we can go from elts in [i,reach]
            farthest = Math.max(farthest, i + nums[i]);

            // Beyond the range of last elt, increment step and switch to next
            if (i == reach) {
                reach = farthest;
                step++;
            }
        }
        return step;
    }

    // My 1AC
    public int jump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // State f(n): minimum number of jumps to reach point N
        // State transfer: f(n) = f(n-1)+1 or f(n-1) (if N is reached in one step but not the first one)

        int fn = 0, reach = 0, next = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                fn = fn + 1;
                reach = next;
            }
            next = Math.max(next, i + nums[i]);
        }
        return fn;
    }

}
