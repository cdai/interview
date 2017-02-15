package advanced.scan.sequence.lc414_thirdmaximumnumber;

/**
 * Given a non-empty array of integers, return the third maximum number in this array.
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 */
public class Solution {

    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        for (int num : nums) {
            if (num == max1 || num == max2 || num == max3) continue;
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            } /* else discard */
        }
        return max3 == Long.MIN_VALUE ? (int) max1 : (int) max3;
    }

}
