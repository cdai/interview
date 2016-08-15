package advanced.scan.twopointers.lc016_threesumclosest;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().threeSumClosest(new int[]{-1, 2, 1, 4}, 1));
    }

    // My 2nd: O(N^2), with duplicate bypass, performance is up from 44% to 54%
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int gap = target - nums[i] - nums[lo] - nums[hi];
                if (gap > 0) {
                    lo++;
                    while (lo < hi && nums[lo] == nums[lo - 1]) { // This is optional
                        lo++;
                    }
                } else if (gap < 0) {
                    hi--;
                    while (lo < hi && nums[hi] == nums[hi + 1]) {
                        hi--;
                    }
                } else {    // For this problem, it's ok not to skip duplicate in these 3 branches if performance is fair
                    return target;
                }
                if (Math.abs(closest) > Math.abs(gap)) {
                    closest = gap;
                }
            }
        }
        return target - closest;
    }

    // My 1st
    public int threeSumClosest1(int[] nums, int target) {
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
