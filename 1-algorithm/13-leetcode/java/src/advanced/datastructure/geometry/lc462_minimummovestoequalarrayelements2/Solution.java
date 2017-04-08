package advanced.datastructure.geometry.lc462_minimummovestoequalarrayelements2;

import java.util.Arrays;

/**
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal,
 * where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 */
public class Solution {

    // Same as best meeting point
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mov = 0, n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            mov += nums[j] - nums[i];
        }
        return mov;
    }

}
