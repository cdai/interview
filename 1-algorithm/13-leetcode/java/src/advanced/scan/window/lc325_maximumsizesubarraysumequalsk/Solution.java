package advanced.scan.window.lc325_maximumsizesubarraysumequalsk;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k.
 * If there isn’t one, return 0 instead.
 */
public class Solution {

    public int maxLen(int[] nums, int k) {
        int sum = 0, from = 0, win = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= k) {
                if (sum == k) win = Math.max(win, i - from + 1);
                sum -= nums[from++];
            }
        }
        return (win == Integer.MAX_VALUE) ? 0 : win;
    }

}
