package advanced.greedy.lc330_patchingarray;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array
 * such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array.
 * Return the minimum number of patches required.
 *
 * Example 1: nums = [1, 3], n = 6. Return 1.
 *
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6]. So we only need 1 patch.
 */
public class Solution {

    // eg.[1,2,3,9], n=100
    // miss=1  -> 2, i=0
    // miss=2  -> 4, i=1
    // miss=4  -> 7, i=2
    // miss=7  -> 14, i=2 (Patch here!)
    // miss=14 -> 23, i=3
    // miss=23 -> 46, i=3 (Patch here!)
    // ...
    // Why is it possible to move 7 to 14 after patch? Since [1,6] is covered which all combinations cover [1,13] plus 7.
    public int minPatches(int[] nums, int n) {
        int patch = 0, i = 0; // long for overflow, eg.nums=[1,2,31,33],n=2147483647
        for (long miss = 1; miss <= n; ) { /* invariant: [1,miss) is covered */
            if (i >= nums.length || miss < nums[i]) {
                miss += miss; // patch miss, now we reach miss-1 (already reached) + miss + 1 (next miss)=2*miss
                patch++;
            } else {
                miss += nums[i++]; // miss >= nums[i], [1,miss) + nums[i] covers miss and move forward
            }
        }
        return patch;
    }

}
