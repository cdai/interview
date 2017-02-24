package miscellaneous.math.statistics.lc268_missingnumber;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * For example, given nums = [0, 1, 3] return 2.
 * Note: Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 */
public class Solution {

    // 3AC.
    public int missingNumber(int[] nums) {
        int missing = 0;
        for (int i = 1; i <= nums.length; i++) {
            missing ^= nums[i - 1] ^ i;
        }
        return missing;
    }

    // My 2nd
    public int missingNumber3(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        // It should be (1 + N) * N / 2
        return (1 + nums.length) * nums.length / 2 - sum;
    }

    // Bit operating solution: same as Single Number
    // Eg.[0,1,3] ^ [0,1,2,4] = 2
    public int missingNumber2(int[] nums) {
        int missing = 0, all = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= nums[i];
            all ^= i;
        }
        return missing ^ all;
    }

    // My 1st
    public int missingNumber1(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int n = nums.length;
        int total = (1 + n) * n / 2;
        return total - sum;
    }

}
