package miscellaneous.interval.lc163_missingranges;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 * For example, Given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99));
        System.out.println(sol.findMissingRanges(new int[]{0, 1, 3, 50, 75}, -2, 99));
        System.out.println(sol.findMissingRanges(new int[]{0}, 0, 0));
    }

    // Concise solution by reusing param.
    // Determine variable meaning at very first: lower => starting point to handle. (old-lower,lower) is done.
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> missing = new ArrayList<>();
        for (int num : nums) {
            if (lower < num) {
                if (lower == num - 1) missing.add(String.valueOf(lower));
                else missing.add(String.format("%d->%d", lower, num - 1));
            } /* else lower=num */
            lower = num + 1;
        }
        if (lower == upper) missing.add(String.valueOf(lower));
        else if (lower < upper) missing.add(String.format("%d->%d", lower, upper));
        /* else lower > upper */
        return missing;
    }

    public List<String> missingRanges_1(int[] nums, int lower, int upper) {
        List<String> missing = new ArrayList<>();
        for (int i = lower, j = 0; i <= upper || j < nums.length; i = nums[j] + 1, j++) {
            if (j == nums.length) {
                add(missing, i, upper);
                break;
            }
            if (i < nums[j])
                add(missing, i, nums[j] - 1);
        }
        return missing;
    }

    public List<String> missingRanges_12(int[] nums, int lower, int upper) {
        List<String> missing = new ArrayList<>();
        if (lower < nums[0]) add(missing, lower, nums[0]);

        Integer prev = null;
        for (int num : nums) {
            if (prev != null && prev != num - 1)
                add(missing, prev, num);
            prev = num;
        }

        if (nums[nums.length - 1] < upper)
            add(missing, nums[nums.length - 1] + 1, upper + 1);
        return missing;
    }

    private void add(List<String> missing, int from, int to) {
        missing.add(from == to ? String.valueOf(from) : from + "->" + to);
    }

}
