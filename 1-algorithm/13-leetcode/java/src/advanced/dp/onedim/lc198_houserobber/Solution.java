package advanced.dp.onedim.lc198_houserobber;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
 * is that adjacent houses have security system connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 */
public class Solution {

    public int rob(int[] nums) {
        int rob = 0, notrob = 0;
        for (int num : nums) {
            int prerob = rob;
            rob = notrob + num;                 // didn't rob last one, so rob current house
            notrob = Math.max(notrob, prerob);  // robbed last one, so can't rob current house
        }
        return Math.max(rob, notrob);
    }

    // My 2AC: O(1) space, only care about fn-1 and fn-2
    public int rob2(int[] nums) {
        int money1 = 0, money2 = 0;
        for (int num : nums) {
            int money = Math.max(money1, money2 + num);
            money2 = money1;
            money1 = money;
        }
        return money1;
    }

    // My 1AC
    public int rob1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] max = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int max1 = (i > 0) ? max[i - 1] : 0;
            int max2 = (i > 1) ? max[i - 2] : 0;
            max[i] = Math.max(max1, max2 + nums[i]);
        }
        return max[max.length - 1];
    }

    public int rob12(int[] nums) {
        return rob2(nums, 0);
    }

    // TLE, rob(k+2) and rob(k+3) share some duplicate recursion
    private int rob2(int[] nums, int k) {
        if (k >= nums.length) {
            return 0;
        }
        if (k == nums.length - 1) {
            return nums[k];
        }

        // k < nums.length - 1: k and k + 1 exist
        int first = rob2(nums, k + 2);
        int second = rob2(nums, k + 3);
        return Math.max(nums[k + 1] + second, Math.max(nums[k] + first, nums[k] + second));
    }

}
