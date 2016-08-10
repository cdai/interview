package advanced.dyprog.onedim.lc045_jumpgame2;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example: Given array A = [2,3,1,1,4]. The minimum number of jumps to reach the last index is 2.
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 *  Note: You can assume that you can always reach the last index.
 */
public class Solution {

    public int jump(int[] nums) {
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
