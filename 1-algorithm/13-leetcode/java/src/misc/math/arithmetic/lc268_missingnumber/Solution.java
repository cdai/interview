package misc.math.arithmetic.lc268_missingnumber;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * For example, given nums = [0, 1, 3] return 2.
 * Note: Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 */
public class Solution {

    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int n = nums.length;
        int total = (1 + n) * n / 2;
        return total - sum;
    }

}
