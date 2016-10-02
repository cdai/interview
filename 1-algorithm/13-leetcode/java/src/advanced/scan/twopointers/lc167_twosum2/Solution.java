package advanced.scan.twopointers.lc167_twosum2;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that
 * they add up to a specific target number. The function twoSum should return indices of the two numbers
 * such that they add up to the target, where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class Solution {

    // My 3AC
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] < target) i++;
            else if (nums[i] + nums[j] > target) j--;
            else return new int[] { i + 1, j + 1 };
        }
        return new int[] { -1, -1 };
    }

    // My 2AD: O(N) scan algorithm
    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] < target) {
                i++;
            } else if (nums[i] + nums[j] > target) {
                j--;
            } else { /* nums[i] + nums[j] == target */
                return new int[] { i + 1, j + 1 };
            }
        }
        return new int[] { -1, -1 };
    }

}
