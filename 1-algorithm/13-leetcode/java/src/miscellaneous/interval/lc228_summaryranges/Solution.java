package miscellaneous.interval.lc228_summaryranges;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class Solution {

    // My 3AC: check from/prev backward at nums[i]
    // from: start point of range, prev: previous num for consecutive check
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        List<String> ranges = new ArrayList<>();
        int from = nums[0], prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (prev < nums[i] - 1) {
                ranges.add(from == prev ? String.valueOf(from) : String.format("%d->%d", from, prev));
                from = prev = nums[i];
            } else prev = nums[i];
        }
        ranges.add(from == prev ? String.valueOf(from) : String.format("%d->%d", from, prev));
        return ranges;
    }

    // My 2AC: much simpler by handling last batch in same loop
    public List<String> summaryRanges2(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        List<String> result = new ArrayList<>();
        for (int i = 0, begin = nums[0]; i < nums.length; i++) {
            if (i == nums.length - 1 || nums[i] + 1 < nums[i + 1]) {
                result.add(begin == nums[i] ? String.valueOf(nums[i]) : begin + "->" + nums[i]);
                if (i < nums.length - 1) {
                    begin = nums[i + 1];
                }
            }
        }
        return result;
    }

    // My 1AC
    public List<String> summaryRanges1(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                continue;
            }
            if (start == nums[i - 1]) {
                result.add(String.valueOf(start));
            } else {
                result.add(start + "->" + nums[i - 1]);
            }
            start = nums[i];
        }

        // Don't forget the last batch
        if (start == nums[nums.length - 1]) {
            result.add(String.valueOf(start));
        } else {
            result.add(start + "->" + nums[nums.length - 1]);
        }
        return result;
    }

}
