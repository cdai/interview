package advanced.combinatorial.permutation.lc031_nextpermutation;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *  1,2,3 → 1,3,2
 *  3,2,1 → 1,2,3
 *  1,1,5 → 1,5,1
 */
public class Solution {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2, j = nums.length - 1;

        // Find backwards the num that breaks decreasing order
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        // Swap it with the cloeset bigger (skip same to avoid duplicate next permutation) num located after it
        if (i >= 0) {
            while (nums[i] >= nums[j]) j--;
            swap(nums, i, j);
        }

        // Reverse all the nums after it
        for (int start = i + 1, end = nums.length - 1; start < end; start++, end--)
            swap(nums, start, end);
    }

    // My 2AC
    // Eg.1243 -> i=>2, j=>3 -> 1342 -> 1324
    // Test case: [1], [1,2], [1,2,3,4], [1,2,4,3]...
    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2, j = nums.length - 1;

        // 1.Find first number (i) which breaks descending order
        for (; i >= 0 && nums[i] >= nums[i + 1]; i--);

        // 2.Exchange this number with the least number that's greater than this number.
        if (i >= 0) {
            while (nums[i] >= nums[j]) j--;
            swap(nums, i, j);
        }

        // 3.Reverse sort the numbers after the exchanged number
        i++;
        for (int k = nums.length - 1; i < k; i++, k--)
            swap(nums, i, k);
    }

    // My 1AC
    public void nextPermutation1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }

        // 1.Find digit from right to left
        int i = nums.length - 2;
        while (i > 0 && nums[i] >= nums[i+1]) {
            i--;
        }

        // 2.Find first larger digit from right to left
        int j = nums.length - 1;
        while (i < j && nums[i] >= nums[j]) { // error1: duplicates [3,3,2,1]
            j--;
        }

        // 3.Swap them
        swap(nums, i, j);

        // 4.Reverse [i+1, n)
        i = (i == j) ? 0 : (i+1);
        for (j = nums.length-1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
