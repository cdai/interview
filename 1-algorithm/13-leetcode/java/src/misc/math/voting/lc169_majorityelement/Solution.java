package misc.math.voting.lc169_majorityelement;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class Solution {

    // 1.O(n),O(n): use hash to count each num occurence
    // 2.O(nlogn),O(logn): sort, then pick median as majority
    // 3.O(n),O(1): linear time majority vote algorithm
    public int majorityElement(int[] nums) {
        int candidate = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (cnt == 0) {
                candidate = nums[i];
            }
            cnt = (nums[i] == candidate) ? cnt + 1 : cnt - 1;
        }
        return candidate;
    }
    // Why it's correct? Because |majority number| > N/2, they can cancel out all of other numbers
    // So at the end, the final candidate must be majority with counter > 0

}
