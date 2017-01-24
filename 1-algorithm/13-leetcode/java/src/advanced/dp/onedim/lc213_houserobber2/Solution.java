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
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(rob(nums, 1, n), rob(nums, 0, n - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int rob = 0, notrob = 0;
        for (int i = start; i < end; i++) {
            int prerob = rob;
            rob = notrob + nums[i];
            notrob = Math.max(notrob, prerob);
        }
        return Math.max(rob, notrob);
    }

    // My 2AC: not 100% understand why it is correct. O(N) time
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0]; // error: Eg.[1] -> 0 which is wrong!
        return Math.max(
                doRob(nums, 1, nums.length),
                doRob(nums, 0, nums.length - 1)
        );
    }

    private int doRob(int[] nums, int start, int end) {
        int money1 = 0, money2 = 0;
        for (int i = start; i < end; i++) {
            int money = Math.max(money1, money2 + nums[i]);
            money2 = money1;
            money1 = money;
        }
        return money1;
    }

    // My 1AC: too many redundant check and edge case
    public int rob1(int[] nums) {
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

    private int rob1(int[] nums, int from, int to) {
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
