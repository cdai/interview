package advanced.scan.singlepointer.lc152_maximumproductsubarray;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{2,3,-2,-3,4}));
    }


    private static final int INVALID = 1;

    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // Three parts: prodBefore, maxSoFar, prodAfter
        // prodBefore<0 or prodAfter<0 or both are nil range
        int prodBefore = INVALID, maxSoFar = nums[0], prodAfter = INVALID;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {

            } else {

            }
        }
        return maxSoFar;
    }

    public int maxProduct2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0], maxEndHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int oldEndHere = maxEndHere;
            int oldSoFar = maxSoFar;

            maxEndHere = Math.max(nums[i] * maxEndHere, nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndHere);

            // Must be different range, which means we miss maxSoFar*maxEndHere*nums[i]
            if (oldEndHere != oldSoFar) {
                int maxAll = oldSoFar * oldEndHere * nums[i];
                if (maxAll > maxSoFar) {
                    maxSoFar = maxEndHere = maxAll;
                }
            }
        }
        return maxSoFar;
    }

    public int maxProduct3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0], maxEndHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 4 candidates: maxSoFar, prod1, prod2 and nums[i] (nums in [0,maxSoFar) and [maxEndHere,i) are impossible)
            int prod1 = nums[i] * maxEndHere;
            int prod2 = (maxEndHere == maxSoFar) ? prod1 : (prod1 * maxSoFar);
            int oldMax = maxSoFar;
            maxEndHere = Math.max(prod1, nums[i]);
            maxEndHere = Math.max(maxEndHere, prod2);
            maxSoFar = Math.max(maxSoFar, maxEndHere);

            // prod1, prod2, i, all these three contain i, which means maxEndHere=maxSoFar when any of them is max
            // So only when maxSoFar is the max, maxEndHere=prod1
            maxEndHere = (maxSoFar == oldMax) ? prod1 : maxSoFar;
        }
        return maxSoFar;
    }

}
