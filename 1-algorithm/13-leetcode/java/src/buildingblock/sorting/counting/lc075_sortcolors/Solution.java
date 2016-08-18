package buildingblock.sorting.counting.lc075_sortcolors;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 */
public class Solution {

    // Partition like quicksort/select, but with one more segment
    public void sortColors(int[] nums) {
        // [0,zero) - red, [zero, one) - white, [one,N) - blue
        int zero = 0, one = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                swap(nums, one, i);
                swap(nums, zero++, one++);
            } else if (nums[i] == 1) {
                swap(nums, one++, i);
            } /* two, leave it there */
        }
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    // My 1st: easy radix sort
    public void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] count = new int[3];
        for (int n : nums) {
            count[n]++;
        }

        int i = 0;
        for (int j = 0; j < count.length; j++) {
            while (count[j]-- > 0) {
                nums[i++] = j;
            }
        }
    }

}
