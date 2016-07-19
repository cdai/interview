package fundamentals.array.lc016_threesumclosest;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        assert nums.length > 2;

        Arrays.sort(nums);

        Integer closest = null;     // Too careful not use INT_MAX
        for (int i = 0; i < nums.length - 2; i++) {
            // Simplify: find sum of two closest to (target - nums[i])
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    closest = getClosest(closest, sum, target);
                    do { j++; } while (j < k && nums[j] == nums[j - 1]);
                } else if (sum > target) {
                    closest = getClosest(closest, sum, target);
                    do { k--; } while (j < k && nums[k] == nums[k + 1]);
                } else {
                    return target;
                }
            }
        }
        return closest;
    }

    private int getClosest(Integer closest, int sum, int target) {
        if (closest == null) {
            return sum;
        }
        return Math.abs(closest - target) < Math.abs(sum - target) ? closest : sum;
    }

}
