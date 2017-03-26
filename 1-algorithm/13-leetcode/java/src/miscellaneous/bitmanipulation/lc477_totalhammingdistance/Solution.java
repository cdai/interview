package miscellaneous.bitmanipulation.lc477_totalhammingdistance;

/**
 * Find the total Hamming distance between all pairs of the given numbers.
 */
public class Solution {

    // O(N) time since 32*O(N).
    // "If there are n integers in array and k of them have a particular bit set and (n-k) do not
    // then that bit contributes k*(n-k) hamming distance to the total."
    public int totalHammingDistance(int[] nums) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < 32; i++) {
            int b1 = 0;
            for (int num : nums) {
                b1 += (num >>> i) & 1;
            }
            cnt += b1 * (n - b1);
        }
        return cnt;
    }

}
