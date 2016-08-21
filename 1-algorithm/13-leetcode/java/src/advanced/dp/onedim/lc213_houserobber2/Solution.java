package advanced.dp.onedim.lc213_houserobber2;

/**
 * Note: This is an extension of House Robber.
 * After robbing those houses on that street, the thief has found himself a new place for his thievery
 * so that he will not get too much attention. This time, all houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same
 * as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{3, 5, 7, 1, 12, 10}));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // nums.length > 2 => rob(0,>0) and rob(1,>1)
        return Math.max(
                rob(nums, 0, nums.length - 2),
                rob(nums, 1, nums.length - 1)
        );
    }

    private int rob(int[] nums, int from, int to) {
        int length = to - from + 1;
        int[] max = new int[length];
        for (int i = from; i <= to; i++) {
            int max1 = (i > from) ? max[i - from - 1] : 0;
            int max2 = (i > from + 1) ? max[i - from - 2] : 0;
            max[i - from] = Math.max(max1, nums[i] + max2);
        }
        return max[length - 1];
    }

}
