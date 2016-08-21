package fundamentals.array.lc189_rotatearray;

/**
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note: Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Hint: Could you do it in-place with O(1) extra space?
 */
public class Solution {

    // Solution 1: just use extra space
    // Solution 2: magical reverse! O(N)
    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length);
        reverse(nums, 0, k);
        reverse(nums, k, nums.length);
    }

    private void reverse(int[] nums, int start, int end) { // exclusive
        for (int i = start, j = end - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // Solution 3: Shift, of course TLE for large array. O(kN)
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        while (k-- > 0) {
            int last = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 0; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = last;
        }
    }

    public void rotate_old(int[] nums, int k) {
        if (nums.length == 0 || k < 0) {
            return;
        }

        int n = nums.length;
        int pivot = n - (k % n);

        // 1.Reverse first half: [d,c,b,a,e,f,g]
        reverse_old(nums, 0, pivot - 1);

        // 2.Reverse second half: [d,c,b,a,g,f,e]
        reverse_old(nums, pivot, n - 1);

        // 3.Reverse entire array: [e,f,g,a,b,c,d]
        reverse_old(nums, 0, n - 1);
    }

    private void reverse_old(int[] nums, int i, int j) {
        for ( ; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    // [1,2,3,4,5,6],k=2 totally wrong!
    public void rotate_old2(int[] nums, int k) {
        if (nums.length == 0 || k < 0) {
            return;
        }

        // Solution-1 O(n*n) time O(1) space: Insert num to target position and shift others right, like insertion sort
        // Solution-2 O(n) time O(n) space: Copy k nums, move others to target position then copy back
        // Solution-3 O(n) time O(1) space
        int n = nums.length;
        //int i = Math.abs(n - k) % nums.length; // error1: k could ==0 or >nums.length
        int i = n - k % n;
        int last = nums[i];
        while (n-- > 0) {
            int target = (i + k) % nums.length;
            int tmp = nums[target];
            nums[target] = last;
            last = tmp;
            i = target;
        }
    }

}
