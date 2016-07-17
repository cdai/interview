package buildingblock.sorting.radix.lc075_sortcolors;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 */
public class Solution {

    public void sortColors(int[] nums) {
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
