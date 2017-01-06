package fundamentals.array.lc027_removeelement;

/**
 * Given an fundamentals.array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another fundamentals.array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * Example:
 *  Given input fundamentals.array nums = [3,2,2,3], val = 3.
 *  Your function should return length = 2, with the first two elements of nums being 2.
 */
public class Solution {

    public int removeElement(int[] A, int elem) {
        int i = 0; /* invariant: [0,i) not contain elem */
        for (int a : A)
            if (a != elem) A[i++] = a;
        return i;
    }

    // My 2nd: use two pointers to do in-place move
    public int removeElement3(int[] nums, int val) {
        // Invariant: [0,j) has no element of val
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    // My 1st
    public int removeElement1(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int j = nums.length-1;
        for (int i = 0; i < j; i++) {
            if (nums[i] == val) {
                while (nums[j] == val && i < j) { // error1: forget i < j bound
                    j--;
                }

                if (i == j) {
                    break;
                }
                nums[i] = nums[j];
                j--;
            }
        }

        if (nums[j] == val) { // error2: off-by-one since both i++ or j-- could terminate loop
            return j;
        }
        return j + 1;
    }
}
