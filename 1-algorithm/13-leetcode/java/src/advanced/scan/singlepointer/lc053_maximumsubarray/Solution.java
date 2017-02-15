package advanced.scan.singlepointer.lc053_maximumsubarray;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
public class Solution {

    // Inspired from leetcode discuss, O(NlogN) solution
    // The key is how to compute maxMiddle()
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }
        int mid = low + (high - low) / 2;
        int max1 = maxSubArray(nums, low, mid);
        int max2 = maxSubArray(nums, mid + 1, high);
        int max3 = maxMiddle(nums, low, mid, high);
        return Math.max(max1, Math.max(max2, max3));
    }

    private int maxMiddle(int[] nums, int low, int mid, int high) {
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid, sum = 0; i >= low; i--) {
            sum += nums[i];
            leftMax = Math.max(leftMax, sum);
        }
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid + 1, sum = 0; i <= high; i++) {
            sum += nums[i];
            rightMax = Math.max(rightMax, sum);
        }
        return leftMax + rightMax;
    }

    // My 2nd: it's easy to comes up this one
    // sumEndHere < 0 means leave nums[i+1] itself alone
    public int maxSubArray2(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE, sumEndHere = 0;
        for (int num : nums) {
            sumEndHere += num;
            maxSoFar = Math.max(maxSoFar, sumEndHere);
            if (sumEndHere < 0)
                sumEndHere = 0;
        }
        return maxSoFar;
    }

    // 3AC.
    // My 2nd: O(N) time, from <Programming Pearls>, but this one is hard to come up
    public int maxSubArray_pearls(int[] nums) {
        int maxSoFar = Integer.MIN_VALUE, maxEndHere = 0;
        for (int num : nums) {
            maxEndHere = Math.max(maxEndHere + num, num);   // discard maxEnd or not
            maxSoFar = Math.max(maxSoFar, maxEndHere);      // global max
        }
        return maxSoFar;
    }

    // My 1st: hard to come up
    public int maxSubArray1(int[] nums) {
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
            ending = Math.max(ending + nums[i], nums[i]); // it makes sense both maxSoFar and maxEndHere = nums[i]
            fn = Math.max(fn, ending);
        }
        return fn;
    }

    public int maxSubArray1_2(int[] nums) {
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
