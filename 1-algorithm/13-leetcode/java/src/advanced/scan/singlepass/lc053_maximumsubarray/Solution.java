package advanced.scan.singlepass.lc053_maximumsubarray;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // [...,f(n-1),...,ending(n-1),nums[i],...]
        // Potential largest segment:
        //  1.f(n-1)
        //  2.ending(n): ending(n-1)+nums[i], means max ending with I
        //  3.f(n-1)+ending(n-1): impossible! otherwise f(n-1) is incorrect!
        //  4.f(n-1)+ending(n): ignored! ending(n) includes f(n-1) if so
        // So, f(n) = max(f(n-1), ending)
        int fn = nums[0], ending = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ending = Math.max(ending + nums[i], nums[i]);
            fn = Math.max(fn, ending);
        }
        return fn;
    }

    public int maxSubArray2(int[] nums) {
        // State f(n) means: largest sum of nums[0..n]
        // State transfers: f(n) = max(f(n), f(n) + tail + nums[i], nums[i])
        // Q: part of tail + nums[i] could be largest? [2,-1,1,10],f(2)=2,f(3)=11

        int fn = nums[0], tail = 0;
        for (int i = 1; i < nums.length; i++) {
            if (tail + nums[i] > 0) {
                fn = Math.max(fn + tail + nums[i], nums[i]);
                tail = 0;
            } else {
                fn = Math.max(fn, nums[i]);
                tail = (fn == nums[i]) ? 0 : tail + nums[i];
            }
        }
        return fn;
    }

}
