package advanced.scan.window.lc209_minimumsizesubarraysum;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * More practice: If you have figured out the O(n) solution,
 * try coding another solution of which the time complexity is O(nlogn).
 */
public class Solution {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0 || s <= 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int sum = 0;                                    // sum = nums[i,j)
        for (int i = 0, j = 0; j <= nums.length; ) {    // Note: give [i,nums.length) a chance, if it falls into sum<s, then break
            if (sum < s) {
                if (j == nums.length) {
                    break;
                }
                sum += nums[j++];
            } else {
                min = Math.min(min, j - i);
                sum -= nums[i++];
            }
        }
        return (min == Integer.MAX_VALUE) ? 0 : min;
    }

    public int minSubArrayLen2(int s, int[] nums) {
        return minLen(nums, s, 0, nums.length - 1);
    }

    private int minLen(int[] nums, int s, int low, int high) {
        if (low == high) {
            return nums[low] >= s ? 1 : Integer.MAX_VALUE;
        }

        int mid = (low + high) / 2;
        int left = minLen(nums, s, low, mid);
        int right = minLen(nums, s, mid + 1, high);

        int min = Math.min(left, right);
        if (min == 1) {
            return min;
        }
        int start = mid, end = mid + 1;
        // How to calculate min length of subarray including mid,mid+1...
        return 1;
    }

}
