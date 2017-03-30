package fundamentals.array.lc485_maxconsecutiveones;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 */
public class Solution {

    // Update max on each 1
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, cnt = 0;
        for (int num : nums) {
            cnt = (num == 0) ? 0 : cnt + 1;
            max = Math.max(max, cnt);
        }
        return max;
    }

    // Update max only upon 0
    public int findMaxConsecutiveOnes_my(int[] nums) {
        int max = 0, cnt = 0, n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (i == n || nums[i] == 0) {
                if (cnt > 0) {
                    max = Math.max(max, cnt);
                    cnt = 0;
                }
            } else {
                cnt++;
            }
        }
        return max;
    }

}
