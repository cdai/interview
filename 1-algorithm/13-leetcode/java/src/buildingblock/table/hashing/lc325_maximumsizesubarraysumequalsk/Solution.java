package buildingblock.table.hashing.lc325_maximumsizesubarraysumequalsk;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn’t one, return 0 instead.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
        System.out.println(sol.maxSubArrayLen(new int[]{-2, -1, 2, 1}, 1));
    }

    // Nice solution: map stores seen <sum, index> before current i
    // Every time not only check sum, but also check sum-k if sum!=k
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> seen = new HashMap<>();
        int max = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) max = i + 1; /* i+1 must be longest by now, so no need to max() */
            else max = Math.max(max, i - seen.getOrDefault(sum - k, i)); /* [0,j]=sum-k, (j,i]=k */
            seen.putIfAbsent(sum, i);
        }
        return max;
    }

    // Wrong! Array contains negative number!!!
    public int maxLen_wrong(int[] nums, int k) {
        int from = 0, win = Integer.MIN_VALUE;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > k)
                sum -= nums[from++];
            if (sum == k)
                win = Math.max(win, i - from + 1);
        }
        return (win == Integer.MIN_VALUE) ? 0 : win;
    }

}
