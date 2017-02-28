package advanced.greedy.lc435_nonoverlappingintervals;

import miscellaneous.interval.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 */
public class Solution {

    // 1.Why {all intervals} - {max compatible intervals} = minimum deleted intervals?
    //  Suppose interval A in the latter max compatible set B and A causes two other intervals be deleted.
    //  If we delete A instead and insert those two deleted intervals to B can obtain a larger set,
    //  then it contradicts B is the max compatible intervals.
    // 2.Why sort by finish time can get max compatible intervals? Refer to CLRS Theorem 16.1.
    // Briefly speaking, if earliest finished is not included, we can always replace the first interval in the set with it.
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i.end));
        int max = 0, lastend = Integer.MIN_VALUE;
        for (Interval in : intervals) {
            if (lastend <= in.start) {
                lastend = in.end;
                max++;
            }
        }
        return intervals.length - max;
    }
}
