package miscellaneous.math.statistics.lc169_majorityelement;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class Solution {

    // My 2AC: bitwise solution
    public int majorityElement(int[] nums) {
        int major = 0;
        for (int i = 0; i < 32; i++) {
            // 1.Get occurence of 1's at ith bit
            int count = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    count++;
                }
            }

            // 2.If > n/2, majority must have it too
            if (count * 2 > nums.length) {
                major |= (1 << i);
            }
        }
        return major;
    }

    // 1.O(n),O(n): use hash to count each num occurence
    // 2.O(nlogn),O(logn): sort, then pick median as majority
    // 3.O(n),O(1): linear time majority vote algorithm
    public int majorityElement1(int[] nums) {
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
