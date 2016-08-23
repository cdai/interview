package advanced.scan.twopasses.lc238_productofarrayexceptself;

import java.util.Arrays;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i]. Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * Follow up: Could you solve it with constant space complexity?
 * (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class Solution {

    // My 2AC: O(N) time with a little improvement (no fill(1) ahead)
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0, prod = 1; i < nums.length; i++) {
            result[i] = prod;
            prod *= nums[i];
        }
        for (int i = nums.length - 1, prod = 1; i >= 0; i--) {
            result[i] *= prod;
            prod *= nums[i];
        }
        return result;
    }

    // My 1AC
    public int[] productExceptSelf1(int[] nums) {
        int[] prod = new int[nums.length];
        Arrays.fill(prod, 1);

        // Invariant: sum(prod[i]) = product of number [0,i-1]
        for (int i = 1, sum = 1; i < nums.length; i++) {
            sum *= nums[i - 1];
            prod[i] *= sum;
        }

        // Invariant: sum = product of number [i+1,len-1]
        // prod[i] = product of number [0,i-1] * [i+1,len-1]
        for (int i = nums.length - 2, sum = 1; i >= 0; i--) {
            sum *= nums[i + 1];
            prod[i] *= sum;
        }
        return prod;
    }

}
