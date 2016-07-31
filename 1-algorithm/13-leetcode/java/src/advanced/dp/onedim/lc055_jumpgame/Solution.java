package advanced.dp.onedim.lc055_jumpgame;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 *  A = [2,3,1,1,4], return true.
 *  A = [3,2,1,0,4], return false.
 */
public class Solution {

    public boolean canJump(int[] nums) {
        // f(n) state means: how far you can go at index N
        // Then state changes: f(n) = max(f(n-1), i + nums[n])

        int fn = 0;     // At first only reach index 0
        for (int i = 0; i < nums.length; i++) {
            if (fn < i) {
                return false;
            }
            fn = Math.max(fn, i + nums[i]);
        }
        return true;    // fn >= i(nums.len-1) when loop ends
    }

    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int[] path = new int[nums.length];
        path[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            if (path[i] == 0) {
                continue;
            }

            for (int j = 1; (j <= nums[i]) && (i + j < path.length); j++) {
                path[i + j] = 1;
            }

            if (path[path.length - 1] == 1) {
                return true;
            }
        }
        return (path[path.length - 1] == 1);
    }

}
