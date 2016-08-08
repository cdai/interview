package advanced.scan.singlepointer.lc334_increasingtripletsubsequence;

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

    public boolean increasingTriplet(int[] nums) {
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
