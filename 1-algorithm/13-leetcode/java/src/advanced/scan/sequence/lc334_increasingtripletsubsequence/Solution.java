package advanced.scan.sequence.lc334_increasingtripletsubsequence;

import java.util.Arrays;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should: Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples:
 *  Given [1, 2, 3, 4, 5], return true.
 *  Given [5, 4, 3, 2, 1], return false.
 */
public class Solution {

    // min1 remembers some number. min2 remembers some number > min1 (not necessarily current value of min1. Key!)
    // 2,4,0,2,4 or 2,4,0,5 (at 0, don't clean 2,4 or discard 0, both wrong. Simply set min1=0. min2=4 still).
    // If min2 be updated later so that old min1 and min2 discarded, it's ok, since both min1,min2<old, don't affect if min3 exists.
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min1) min1 = num;        /* num <= min1 */
            else if (num <= min2) min2 = num;   /* min1 < num */
            else return true;                   /* min1 < min2 < num */
        }
        return false;
    }

    // A general K version
    public boolean increasingTriplet_general(int[] nums) {
        int[] triplet = new int[3];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(triplet, 0, len, num);
            if (i < 0) i = -(i + 1);
            triplet[i] = num;
            if (i == len) len++;
            if (len == 3) return true;
        }
        return false;
    }

    // My 2nd: it's still very hard to come up...
    // My 1st
    public boolean increasingTriplet2(int[] nums) {
        int t1 = Integer.MAX_VALUE, t2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= t1) {        // num <= t1 ("eat" duplicate to avoid it become t2 or even return true)
                t1 = num;
            } else {
                if (num <= t2) {    // t1 < num <= t2 (likewise, eat duplicate)
                    t2 = num;
                } else {            // t1 < t2 < num
                    return true;
                }
            }
        }
        return false;
    }

}
