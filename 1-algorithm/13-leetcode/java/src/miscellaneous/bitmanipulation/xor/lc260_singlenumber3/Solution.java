package miscellaneous.bitmanipulation.xor.lc260_singlenumber3;

import java.util.Arrays;

/**
 * Given an array of numbers nums, in which exactly two elements appear only once
 * and all the other elements appear exactly twice. Find the two elements that appear only once.
 * For example: Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * Note:
 *  1.The order of the result is not important. So in the above example, [5, 3] is also correct.
 *  2.Your algorithm should run in linear runtime complexity.
 *      Could you implement it using only constant space complexity?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new Solution().singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }

    // Wow! This one beats 98.7% submission!
    public int[] singleNumber(int[] nums) {
        // 1.Get a^b
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        // 2.Find first different bit, which divides array into two parts
        int i = 0;
        for ( ; (i < 32) && (((xor >> i) & 1) == 0); i++);

        // 3.Get a or b from one part of array
        int single = 0, mask = (1 << i);
        for (int n : nums) {
            if ((n & mask) != 0) { // error1: should be !=0, not ==1. eg.mask=2, n=2, n & mask = 2.
                single ^= n;
            }
        }

        // 4.Get a from b, viceversa by cancelling out it.
        return new int[]{ single, xor ^ single };
    }

}
