package buildingblock.sorting.counting.lc075_sortcolors;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 */
public class Solution {

    // [0, red)=red(0), [red,i)=white(1), [i,blue]=unknown, (blue,n-1]=blue(2)
    // r = where next red to store (A[r]=0 when r==i or A[r]=1 after first 1 occurs)
    // b = where next blue to store (unknown value)
    //       r   i     b
    // 0 0 0 1 1 ? ... ? 2 2 2
    public void sortColors(int[] nums) {
        int r = 0, b = nums.length - 1;
        for (int i = 0; i <= b; i++) {
            if (nums[i] == 0) swap(nums, r++, i); // We know nums[red]=1
            else if (nums[i] == 2) swap(nums, b--, i--); // Continue to handle i after swap to maintain invariant, since we don't know what it(A[b]) is
        }
    }

    // Partition like quicksort/select, but with one more segment
    public void sortColors2(int[] nums) {
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

    // My 1st: easy counter sort
    public void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int[] count = new int[3];
        for (int num : nums)
            count[num]++;

        for (int i = 0, j = 0; j < count.length; j++)
            while (count[j]-- > 0)
                nums[i++] = j;
    }

}
